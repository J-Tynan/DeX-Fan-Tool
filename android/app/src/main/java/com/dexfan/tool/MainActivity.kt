package com.dexfan.tool

import android.graphics.Typeface
import android.hardware.usb.UsbManager
import android.os.Bundle
import android.widget.ScrollView
import android.widget.TextView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    private val usbManager by lazy {
        getSystemService(USB_SERVICE) as? UsbManager
    }

    private lateinit var content: TextView
    private lateinit var probeLogger: ProbeLogger

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        probeLogger = ProbeLogger(filesDir)

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
        val snapshot = buildProbeSnapshot()
        val report = UsbProbeReportFormatter.format(snapshot)
        val logResult = probeLogger.logSnapshot(snapshot, report)
        content.text = UsbProbeReportFormatter.appendLogStatus(report, logResult)
    }

    private fun buildProbeSnapshot(): UsbProbeSnapshot {
        val manager = usbManager
            ?: return UsbProbeSnapshot.managerUnavailable()

        return UsbProbeSnapshot.fromUsbDevices(manager.deviceList.values)
    }
}
