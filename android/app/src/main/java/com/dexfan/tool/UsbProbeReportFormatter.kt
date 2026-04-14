package com.dexfan.tool

import java.util.Locale

object UsbProbeReportFormatter {
    fun format(snapshot: UsbProbeSnapshot): String {
        if (!snapshot.usbManagerAvailable) {
            return """
                DeX Fan Tool
                Read-only USB enumeration mode.

                USB manager unavailable on this device.

                No USB control transfers are sent from this screen.
            """.trimIndent()
        }

        return buildString {
            appendLine("DeX Fan Tool")
            appendLine("Read-only USB enumeration mode.")
            appendLine()

            if (!snapshot.hasDevices) {
                appendLine("No USB devices detected.")
                appendLine()
                appendLine("Connect the dock through a powered hub and reopen this screen.")
                appendLine("No USB control transfers are sent from this screen.")
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
            }

            appendLine()
            appendLine("No USB control transfers are sent from this screen.")
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
}