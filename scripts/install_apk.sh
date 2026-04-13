#!/usr/bin/env sh
set -eu

APK_PATH="${1:-android/app/build/outputs/apk/debug/app-debug.apk}"
SERIAL="${2:-}"

if [ ! -f "$APK_PATH" ]; then
    echo "APK not found: $APK_PATH" >&2
    exit 1
fi

if [ -n "$SERIAL" ]; then
    exec adb -s "$SERIAL" install -r "$APK_PATH"
fi

exec adb install -r "$APK_PATH"
