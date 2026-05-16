package com.dexfan.tool

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Typeface
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager
import android.os.Build
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

    private val requestedPermissionDeviceNames = mutableSetOf<String>()

    private val usbPermissionReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action != usbPermissionAction) {
                return
            }

            val device = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE, UsbDevice::class.java)
            if (device != null) {
                requestedPermissionDeviceNames.remove(device.deviceName)
            }

            refreshContent()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registerUsbPermissionReceiver()

        probeLogger = ProbeLogger(filesDir)

        content = TextView(this).apply {
            text = "DeX Fan Tool\nRead-only USB enumeration and vendor IN probe mode."
            textSize = 16f
            typeface = Typeface.MONOSPACE
            setPadding(48, 48, 48, 48)
            setTextIsSelectable(true)
        }

        setContentView(ScrollView(this).apply { addView(content) })
    }

    override fun onResume() {
        super.onResume()
        refreshContent()
    }

    override fun onDestroy() {
        unregisterReceiver(usbPermissionReceiver)
        super.onDestroy()
    }

    private fun refreshContent() {
        val snapshot = buildProbeSnapshot()
        val report = UsbProbeReportFormatter.format(snapshot)
        val logResult = probeLogger.logSnapshot(snapshot, report)
        content.text = UsbProbeReportFormatter.appendLogStatus(report, logResult)
    }

    private fun buildProbeSnapshot(): UsbProbeSnapshot {
        val manager = usbManager
            ?: return UsbProbeSnapshot.managerUnavailable()

        val devices = manager.deviceList.values

        devices
            .filterNot { manager.hasPermission(it) }
            .filterNot { requestedPermissionDeviceNames.contains(it.deviceName) }
            .forEach { device ->
                requestedPermissionDeviceNames.add(device.deviceName)
                manager.requestPermission(device, buildUsbPermissionIntent())
            }

        val probeStates = UsbReadOnlyProbe.collect(
            usbManager = manager,
            devices = devices,
            requestedPermissionDeviceNames = requestedPermissionDeviceNames,
        )

        return UsbProbeSnapshot.fromUsbDevices(devices, probeStates)
    }

    private fun buildUsbPermissionIntent(): PendingIntent {
        val flags = PendingIntent.FLAG_UPDATE_CURRENT or when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> PendingIntent.FLAG_MUTABLE
            else -> 0
        }

        return PendingIntent.getBroadcast(
            this,
            0,
            Intent(usbPermissionAction).setPackage(packageName),
            flags,
        )
    }

    private fun registerUsbPermissionReceiver() {
        val filter = IntentFilter(usbPermissionAction)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(usbPermissionReceiver, filter, RECEIVER_NOT_EXPORTED)
            return
        }

        registerReceiver(usbPermissionReceiver, filter)
    }

    companion object {
        private const val usbPermissionAction = "com.dexfan.tool.USB_PERMISSION"
    }
}
