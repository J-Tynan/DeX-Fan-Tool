package com.dexfan.tool

import android.hardware.usb.UsbConstants
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager

data class UsbVendorInProbeResult(
    val requestType: Int,
    val request: Int,
    val value: Int,
    val index: Int,
    val requestedLength: Int,
    val actualLength: Int,
    val responseHex: String? = null,
)

data class UsbDeviceProbeState(
    val permissionGranted: Boolean,
    val permissionRequested: Boolean = false,
    val vendorInProbeResults: List<UsbVendorInProbeResult> = emptyList(),
    val attemptedProbeCount: Int = 0,
    val zeroLengthResponseCount: Int = 0,
    val noResponseCount: Int = 0,
    val probeErrorMessage: String? = null,
)

object UsbReadOnlyProbe {
    private const val probeTimeoutMillis = 50
    private const val usbRecipientDevice = 0x00
    private const val usbRecipientInterface = 0x01
    private const val realtekVendorId = 0x0BDA
    private const val realtekProductId = 0x8152
    private const val realtekResponsiveRequest = 0x05
    private const val defaultSweepLength = 16

    // Keep the expansion read-only: vendor IN only, zero value, small request IDs, and known interface indices.
    private val conservativeRequests = 0x00..0x07
    private val deviceRecipientBoundaryLengths = listOf(8, 16, 32, 64, 128, 256, 512, 1024, 2048)
    private val interfaceRecipientBoundaryLengths = listOf(8, 16, 32, 64, 128, 256, 512)

    fun collect(
        usbManager: UsbManager,
        devices: Collection<UsbDevice>,
        requestedPermissionDeviceNames: Set<String>,
    ): Map<String, UsbDeviceProbeState> {
        return devices.associate { device ->
            device.deviceName to buildProbeState(usbManager, device, requestedPermissionDeviceNames)
        }
    }

    private fun buildProbeState(
        usbManager: UsbManager,
        device: UsbDevice,
        requestedPermissionDeviceNames: Set<String>,
    ): UsbDeviceProbeState {
        if (!usbManager.hasPermission(device)) {
            return UsbDeviceProbeState(
                permissionGranted = false,
                permissionRequested = requestedPermissionDeviceNames.contains(device.deviceName),
            )
        }

        val connection = usbManager.openDevice(device)
            ?: return UsbDeviceProbeState(
                permissionGranted = true,
                probeErrorMessage = "Unable to open USB device connection.",
            )

        return try {
            val attempts = buildProbeAttempts(device)
            val responses = mutableListOf<UsbVendorInProbeResult>()
            var zeroLengthResponseCount = 0
            var noResponseCount = 0

            attempts.forEach { attempt ->
                val buffer = ByteArray(attempt.requestedLength)
                val actualLength = connection.controlTransfer(
                    attempt.requestType,
                    attempt.request,
                    attempt.value,
                    attempt.index,
                    buffer,
                    buffer.size,
                    probeTimeoutMillis,
                )

                when {
                    actualLength > 0 -> {
                        responses += UsbVendorInProbeResult(
                            requestType = attempt.requestType,
                            request = attempt.request,
                            value = attempt.value,
                            index = attempt.index,
                            requestedLength = attempt.requestedLength,
                            actualLength = actualLength,
                            responseHex = buffer.copyOf(actualLength).joinToString(" ") { byte ->
                                String.format("%02X", byte.toInt() and 0xFF)
                            },
                        )
                    }
                    actualLength == 0 -> zeroLengthResponseCount += 1
                    else -> noResponseCount += 1
                }
            }

            UsbDeviceProbeState(
                permissionGranted = true,
                vendorInProbeResults = responses,
                attemptedProbeCount = attempts.size,
                zeroLengthResponseCount = zeroLengthResponseCount,
                noResponseCount = noResponseCount,
            )
        } catch (error: SecurityException) {
            UsbDeviceProbeState(
                permissionGranted = true,
                probeErrorMessage = error.message ?: "Security exception while probing.",
            )
        } finally {
            connection.close()
        }
    }

    private fun buildProbeAttempts(device: UsbDevice): List<ProbeAttempt> {
        if (device.vendorId == realtekVendorId && device.productId == realtekProductId) {
            return buildTargetedRealtekAttempts(device)
        }

        return buildConservativeSweepAttempts(device)
    }

    private fun buildConservativeSweepAttempts(device: UsbDevice): List<ProbeAttempt> {
        val attempts = mutableListOf<ProbeAttempt>()

        conservativeRequests.forEach { request ->
            attempts += ProbeAttempt(
                requestType = UsbConstants.USB_DIR_IN or UsbConstants.USB_TYPE_VENDOR or usbRecipientDevice,
                request = request,
                value = 0,
                index = 0,
                requestedLength = defaultSweepLength,
            )
        }

        interfaceNumbers(device).forEach { interfaceNumber ->
            conservativeRequests.forEach { request ->
                attempts += ProbeAttempt(
                    requestType = UsbConstants.USB_DIR_IN or UsbConstants.USB_TYPE_VENDOR or usbRecipientInterface,
                    request = request,
                    value = 0,
                    index = interfaceNumber,
                    requestedLength = defaultSweepLength,
                )
            }
        }

        return attempts
    }

    private fun buildTargetedRealtekAttempts(device: UsbDevice): List<ProbeAttempt> {
        val attempts = mutableListOf<ProbeAttempt>()
        val interfaceNumber = interfaceNumbers(device).firstOrNull() ?: 0

        deviceRecipientBoundaryLengths.forEach { requestedLength ->
            attempts += ProbeAttempt(
                requestType = UsbConstants.USB_DIR_IN or UsbConstants.USB_TYPE_VENDOR or usbRecipientDevice,
                request = realtekResponsiveRequest,
                value = 0,
                index = 0,
                requestedLength = requestedLength,
            )
        }

        interfaceRecipientBoundaryLengths.forEach { requestedLength ->
            attempts += ProbeAttempt(
                requestType = UsbConstants.USB_DIR_IN or UsbConstants.USB_TYPE_VENDOR or usbRecipientInterface,
                request = realtekResponsiveRequest,
                value = 0,
                index = interfaceNumber,
                requestedLength = requestedLength,
            )
        }

        return attempts
    }

    private fun interfaceNumbers(device: UsbDevice): List<Int> {
        return List(device.interfaceCount) { interfaceIndex ->
            device.getInterface(interfaceIndex).id
        }.distinct()
    }

    private data class ProbeAttempt(
        val requestType: Int,
        val request: Int,
        val value: Int,
        val index: Int,
        val requestedLength: Int,
    )
}