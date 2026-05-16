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
    private const val probeBufferLength = 16
    private const val usbRecipientDevice = 0x00
    private const val usbRecipientInterface = 0x01

    // Keep the expansion read-only: vendor IN only, zero value, small request IDs, and known interface indices.
    private val conservativeRequests = 0x00..0x07

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
                val buffer = ByteArray(probeBufferLength)
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
                            requestedLength = buffer.size,
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
        val attempts = mutableListOf<ProbeAttempt>()

        conservativeRequests.forEach { request ->
            attempts += ProbeAttempt(
                requestType = UsbConstants.USB_DIR_IN or UsbConstants.USB_TYPE_VENDOR or usbRecipientDevice,
                request = request,
                value = 0,
                index = 0,
            )
        }

        repeat(device.interfaceCount) { interfaceIndex ->
            val interfaceNumber = device.getInterface(interfaceIndex).id

            conservativeRequests.forEach { request ->
                attempts += ProbeAttempt(
                    requestType = UsbConstants.USB_DIR_IN or UsbConstants.USB_TYPE_VENDOR or usbRecipientInterface,
                    request = request,
                    value = 0,
                    index = interfaceNumber,
                )
            }
        }

        return attempts
    }

    private data class ProbeAttempt(
        val requestType: Int,
        val request: Int,
        val value: Int,
        val index: Int,
    )
}