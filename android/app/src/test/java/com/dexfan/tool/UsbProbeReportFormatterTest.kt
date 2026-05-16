package com.dexfan.tool

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class UsbProbeReportFormatterTest {
    @Test
    fun formatReturnsManagerUnavailableMessage() {
        val report = UsbProbeReportFormatter.format(UsbProbeSnapshot.managerUnavailable())

        assertTrue(report.contains("USB manager unavailable on this device."))
        assertTrue(report.contains("No USB OUT control transfers are sent from this screen."))
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
        assertTrue(report.contains("Read-only vendor IN probe:"))
        assertTrue(report.contains("No probe attempts recorded."))
    }

    @Test
    fun formatShowsPermissionRequestedState() {
        val snapshot = UsbProbeSnapshot(
            usbManagerAvailable = true,
            devices = listOf(
                UsbDeviceSnapshot(
                    deviceName = "/dev/bus/usb/001/002",
                    vendorId = 0x04B4,
                    productId = 0x5210,
                    deviceClass = 0,
                    deviceSubclass = 0,
                    deviceProtocol = 0,
                    interfaces = emptyList(),
                    permissionGranted = false,
                    permissionRequested = true,
                )
            ),
        )

        val report = UsbProbeReportFormatter.format(snapshot)

        assertTrue(report.contains("USB permission requested. Reopen after granting access."))
    }

    @Test
    fun formatShowsProbeResponseDetails() {
        val snapshot = UsbProbeSnapshot(
            usbManagerAvailable = true,
            devices = listOf(
                UsbDeviceSnapshot(
                    deviceName = "/dev/bus/usb/001/002",
                    vendorId = 0x04B4,
                    productId = 0x5210,
                    deviceClass = 0,
                    deviceSubclass = 0,
                    deviceProtocol = 0,
                    interfaces = emptyList(),
                    attemptedProbeCount = 12,
                    zeroLengthResponseCount = 11,
                    noResponseCount = 0,
                    vendorInProbeResults = listOf(
                        UsbVendorInProbeResult(
                            requestType = 0xC0,
                            request = 0x01,
                            value = 0,
                            index = 0,
                            requestedLength = 16,
                            actualLength = 4,
                            responseHex = "01 02 0A FF",
                        )
                    ),
                )
            ),
        )

        val report = UsbProbeReportFormatter.format(snapshot)

        assertTrue(report.contains("Attempted 12 vendor IN probes; non-empty=1, zero-byte=11, no-response=0"))
        assertTrue(report.contains("IN type=0xC0 req=0x01 val=0x0000 idx=0x0000 len=16 -> 4 byte(s): 01 02 0A FF"))
    }

    @Test
    fun formatSummarizesEmptyExpandedProbeRun() {
        val snapshot = UsbProbeSnapshot(
            usbManagerAvailable = true,
            devices = listOf(
                UsbDeviceSnapshot(
                    deviceName = "/dev/bus/usb/001/002",
                    vendorId = 0x04B4,
                    productId = 0x5210,
                    deviceClass = 0,
                    deviceSubclass = 0,
                    deviceProtocol = 0,
                    interfaces = emptyList(),
                    attemptedProbeCount = 24,
                    zeroLengthResponseCount = 0,
                    noResponseCount = 24,
                )
            ),
        )

        val report = UsbProbeReportFormatter.format(snapshot)

        assertTrue(report.contains("Attempted 24 vendor IN probes; non-empty=0, zero-byte=0, no-response=24"))
        assertTrue(report.contains("No non-empty responses captured."))
    }

    @Test
    fun formatTruncatesLongResponsePreview() {
        val longResponse = List(40) { "00" }.joinToString(" ")
        val snapshot = UsbProbeSnapshot(
            usbManagerAvailable = true,
            devices = listOf(
                UsbDeviceSnapshot(
                    deviceName = "/dev/bus/usb/001/002",
                    vendorId = 0x0BDA,
                    productId = 0x8152,
                    deviceClass = 0,
                    deviceSubclass = 0,
                    deviceProtocol = 0,
                    interfaces = emptyList(),
                    attemptedProbeCount = 1,
                    vendorInProbeResults = listOf(
                        UsbVendorInProbeResult(
                            requestType = 0xC0,
                            request = 0x05,
                            value = 0,
                            index = 0,
                            requestedLength = 256,
                            actualLength = 256,
                            responseHex = longResponse,
                        )
                    ),
                )
            ),
        )

        val report = UsbProbeReportFormatter.format(snapshot)

        assertTrue(report.contains("256 byte(s): 00 00 00 00"))
        assertTrue(report.contains("..."))
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

    @Test
    fun formatUsbByteUsesUppercaseHex() {
        assertEquals("0x0B", UsbProbeReportFormatter.formatUsbByte(0x0B))
    }
}