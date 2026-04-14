package com.dexfan.tool

import android.hardware.usb.UsbDevice

data class UsbInterfaceSnapshot(
    val interfaceIndex: Int,
    val interfaceClass: Int,
    val interfaceSubclass: Int,
    val interfaceProtocol: Int,
    val endpointCount: Int,
)

data class UsbDeviceSnapshot(
    val deviceName: String,
    val vendorId: Int,
    val productId: Int,
    val deviceClass: Int,
    val deviceSubclass: Int,
    val deviceProtocol: Int,
    val interfaces: List<UsbInterfaceSnapshot>,
)

data class UsbProbeSnapshot(
    val usbManagerAvailable: Boolean,
    val devices: List<UsbDeviceSnapshot>,
) {
    companion object {
        fun managerUnavailable(): UsbProbeSnapshot {
            return UsbProbeSnapshot(usbManagerAvailable = false, devices = emptyList())
        }

        fun fromUsbDevices(devices: Collection<UsbDevice>): UsbProbeSnapshot {
            val snapshots = devices
                .sortedWith(compareBy<UsbDevice>({ it.vendorId }, { it.productId }, { it.deviceName }))
                .map { device ->
                    UsbDeviceSnapshot(
                        deviceName = device.deviceName,
                        vendorId = device.vendorId,
                        productId = device.productId,
                        deviceClass = device.deviceClass,
                        deviceSubclass = device.deviceSubclass,
                        deviceProtocol = device.deviceProtocol,
                        interfaces = List(device.interfaceCount) { interfaceIndex ->
                            val usbInterface = device.getInterface(interfaceIndex)
                            UsbInterfaceSnapshot(
                                interfaceIndex = interfaceIndex,
                                interfaceClass = usbInterface.interfaceClass,
                                interfaceSubclass = usbInterface.interfaceSubclass,
                                interfaceProtocol = usbInterface.interfaceProtocol,
                                endpointCount = usbInterface.endpointCount,
                            )
                        },
                    )
                }

            return UsbProbeSnapshot(usbManagerAvailable = true, devices = snapshots)
        }
    }

    val hasDevices: Boolean
        get() = devices.isNotEmpty()
}