package com.dexfan.tool

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class UsbProbeReportFormatterTest {
    @Test
    fun formatReturnsManagerUnavailableMessage() {
        val report = UsbProbeReportFormatter.format(UsbProbeSnapshot.managerUnavailable())

        assertTrue(report.contains("USB manager unavailable on this device."))
        assertTrue(report.contains("No USB control transfers are sent from this screen."))
    }

    @Test
    fun formatReturnsDeviceDetailsWithHexIdentifiers() {
        val snapshot = UsbProbeSnapshot(
            usbManagerAvailable = true,
            devices = listOf(
                UsbDeviceSnapshot(
                    deviceName = "/dev/bus/usb/001/002",
                    vendorId = 0x04E8,
                    productId = 0x6860,
                    deviceClass = 0,
                    deviceSubclass = 0,
                    deviceProtocol = 0,
                    interfaces = listOf(
                        UsbInterfaceSnapshot(
                            interfaceIndex = 0,
                            interfaceClass = 255,
                            interfaceSubclass = 1,
                            interfaceProtocol = 1,
                            endpointCount = 2,
                        )
                    ),
                )
            ),
        )

        val report = UsbProbeReportFormatter.format(snapshot)

        assertTrue(report.contains("Detected 1 USB device(s)."))
        assertTrue(report.contains("VID: 0x04E8  PID: 0x6860"))
        assertTrue(report.contains("Interface 0: class=255 subclass=1 protocol=1 endpoints=2"))
    }

    @Test
    fun appendLogStatusIncludesSavedLogPath() {
        val statusReport = UsbProbeReportFormatter.appendLogStatus(
            report = "Base report",
            result = ProbeLogResult(savedFileName = "usb-probe-20260414-101010-000.txt"),
        )

        assertTrue(statusReport.contains("Base report"))
        assertTrue(
            statusReport.contains(
                "Latest log saved to app storage: probe-logs/usb-probe-20260414-101010-000.txt"
            )
        )
    }

    @Test
    fun appendLogStatusIncludesFailureMessage() {
        val statusReport = UsbProbeReportFormatter.appendLogStatus(
            report = "Base report",
            result = ProbeLogResult(errorMessage = "disk full"),
        )

        assertTrue(statusReport.contains("Log save failed: disk full"))
    }

    @Test
    fun formatUsbIdUsesUppercaseHex() {
        assertEquals("0x00AF", UsbProbeReportFormatter.formatUsbId(0xAF))
    }
}