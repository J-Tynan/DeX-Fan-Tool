package com.dexfan.tool

import android.graphics.Typeface
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager
import android.os.Bundle
import android.util.Log
import android.widget.ScrollView
import android.widget.TextView
import androidx.activity.ComponentActivity
import java.util.Locale

class MainActivity : ComponentActivity() {
    private val usbManager by lazy {
        getSystemService(USB_SERVICE) as? UsbManager
    }

    private lateinit var content: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        content = TextView(this).apply {
            text = "DeX Fan Tool\nRead-only USB enumeration mode."
            textSize = 16f
            typeface = Typeface.MONOSPACE
            setPadding(48, 48, 48, 48)
            setTextIsSelectable(true)
        }

        setContentView(ScrollView(this).apply { addView(content) })
    }

    override fun onResume() {
        super.onResume()
        val report = buildDeviceReport()
        content.text = report
        Log.i(TAG, report)
    }

    private fun buildDeviceReport(): String {
        val manager = usbManager
            ?: return """
                DeX Fan Tool
                USB manager unavailable on this device.

                No USB control transfers are sent from this screen.
            """.trimIndent()

        val devices = manager.deviceList.values
            .sortedWith(compareBy<UsbDevice>({ it.vendorId }, { it.productId }, { it.deviceName }))

        return buildString {
            appendLine("DeX Fan Tool")
            appendLine("Read-only USB enumeration mode.")
            appendLine()

            if (devices.isEmpty()) {
                appendLine("No USB devices detected.")
                appendLine()
                appendLine("Connect the dock through a powered hub and reopen this screen.")
                appendLine("No USB control transfers are sent from this screen.")
                return@buildString
            }

            appendLine("Detected ${devices.size} USB device(s).")

            devices.forEachIndexed { index, device ->
                appendLine()
                appendLine("[${index + 1}] ${device.deviceName}")
                appendLine("VID: ${formatUsbId(device.vendorId)}  PID: ${formatUsbId(device.productId)}")
                appendLine(
                    "Class/Subclass/Protocol: ${device.deviceClass}/${device.deviceSubclass}/${device.deviceProtocol}"
                )
                appendLine("Interfaces: ${device.interfaceCount}")

                for (interfaceIndex in 0 until device.interfaceCount) {
                    val usbInterface = device.getInterface(interfaceIndex)
                    appendLine(
                        "  - Interface $interfaceIndex: class=${usbInterface.interfaceClass} " +
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

    private fun formatUsbId(value: Int): String {
        return String.format(Locale.US, "0x%04X", value and 0xFFFF)
    }

    companion object {
        private const val TAG = "DeXFanTool"
    }
}
