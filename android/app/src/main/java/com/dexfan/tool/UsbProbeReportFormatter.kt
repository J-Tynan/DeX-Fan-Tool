package com.dexfan.tool

import java.util.Locale

object UsbProbeReportFormatter {
    fun format(snapshot: UsbProbeSnapshot): String {
        if (!snapshot.usbManagerAvailable) {
            return """
                DeX Fan Tool
                Read-only USB enumeration and vendor IN probe mode.

                USB manager unavailable on this device.

                No USB OUT control transfers are sent from this screen.
            """.trimIndent()
        }

        return buildString {
            appendLine("DeX Fan Tool")
            appendLine("Read-only USB enumeration and vendor IN probe mode.")
            appendLine()

            if (!snapshot.hasDevices) {
                appendLine("No USB devices detected.")
                appendLine()
                appendLine("Connect the dock through a powered hub and reopen this screen.")
                appendLine("No USB OUT control transfers are sent from this screen.")
                return@buildString
            }

            appendLine("Detected ${snapshot.devices.size} USB device(s).")

            snapshot.devices.forEachIndexed { index, device ->
                appendLine()
                appendLine("[${index + 1}] ${device.deviceName}")
                appendLine("VID: ${formatUsbId(device.vendorId)}  PID: ${formatUsbId(device.productId)}")
                appendLine(
                    "Class/Subclass/Protocol: " +
                        "${device.deviceClass}/${device.deviceSubclass}/${device.deviceProtocol}"
                )
                appendLine("Interfaces: ${device.interfaces.size}")

                device.interfaces.forEach { usbInterface ->
                    appendLine(
                        "  - Interface ${usbInterface.interfaceIndex}: class=${usbInterface.interfaceClass} " +
                            "subclass=${usbInterface.interfaceSubclass} " +
                            "protocol=${usbInterface.interfaceProtocol} " +
                            "endpoints=${usbInterface.endpointCount}"
                    )
                }

                appendProbeStatus(device)
            }

            appendLine()
            appendLine("No USB OUT control transfers are sent from this screen.")
        }
    }

    private fun StringBuilder.appendProbeStatus(device: UsbDeviceSnapshot) {
        appendLine("Read-only vendor IN probe:")

        if (!device.permissionGranted) {
            val permissionStatus = if (device.permissionRequested) {
                "USB permission requested. Reopen after granting access."
            } else {
                "USB permission not yet granted."
            }

            appendLine("  - $permissionStatus")
            return
        }

        if (device.probeErrorMessage != null) {
            appendLine("  - ${device.probeErrorMessage}")
            return
        }

        if (device.attemptedProbeCount == 0) {
            appendLine("  - No probe attempts recorded.")
            return
        }

        appendLine(
            "  - Attempted ${device.attemptedProbeCount} vendor IN probes; " +
                "non-empty=${device.vendorInProbeResults.size}, " +
                "zero-byte=${device.zeroLengthResponseCount}, " +
                "no-response=${device.noResponseCount}"
        )

        if (device.vendorInProbeResults.isEmpty()) {
            appendLine("  - No non-empty responses captured.")
            return
        }

        device.vendorInProbeResults.forEach { result ->
            appendLine(
                "  - IN type=${formatUsbByte(result.requestType)} req=${formatUsbByte(result.request)} " +
                    "val=${formatUsbId(result.value)} idx=${formatUsbId(result.index)} -> " +
                    describeProbeResult(result)
            )
        }
    }

    private fun describeProbeResult(result: UsbVendorInProbeResult): String {
        return when {
            result.actualLength > 0 -> "${result.actualLength} byte(s): ${result.responseHex}"
            result.actualLength == 0 -> "0 byte(s)"
            else -> "no response"
        }
    }

    fun appendLogStatus(report: String, result: ProbeLogResult): String {
        return buildString {
            append(report)
            appendLine()
            appendLine()

            if (result.savedFileName != null) {
                appendLine("Latest log saved to app storage: ${ProbeLogger.logDirectoryName}/${result.savedFileName}")
                return@buildString
            }

            appendLine("Log save failed: ${result.errorMessage ?: "unknown error"}")
        }
    }

    internal fun formatUsbId(value: Int): String {
        return String.format(Locale.US, "0x%04X", value and 0xFFFF)
    }

    internal fun formatUsbByte(value: Int): String {
        return String.format(Locale.US, "0x%02X", value and 0xFF)
    }
}