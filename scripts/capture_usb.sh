#!/usr/bin/env sh
set -eu

SERIAL="${1:-}"

if [ -n "$SERIAL" ]; then
    ADB_ARGS="-s $SERIAL"
else
    ADB_ARGS=""
fi

echo "Capturing USB-related logcat output. Press Ctrl+C to stop."
exec adb $ADB_ARGS logcat -v time UsbHostManager:* UsbDeviceManager:* *:S
