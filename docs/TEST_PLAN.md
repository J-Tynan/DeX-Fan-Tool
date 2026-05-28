# Test Plan

## Test environment

- **Dock**: Samsung DeX Station EE‑MG950
- **Phones**: rooted-capable older One UI device (Samsung Galaxy S9 `SM-G960F`) as the primary capture target, Samsung Galaxy Note 9 as the stock fan-positive baseline, and one modern comparison device (Samsung Galaxy S23 Ultra)
- **Tools**: powered USB hub (DeX Station), USB‑C cables, multimeter, bench power supply, optional USB protocol analyzer, optional oscilloscope
- **Software**: Android app (Kotlin) for probing; adb and logcat for logs; rooted Galaxy S9 shell access for `usbmon` on the primary rooted path

## Test cases

1. **Device enumeration**
   - Objective: app lists dock VID/PID and descriptors.
   - Acceptance: device appears in `UsbManager.getDeviceList()` with vendor/product IDs logged.

2. **Safe IN probe**
   - Objective: perform read-only `controlTransfer` probes and log responses.
   - Acceptance: at least one non-empty response captured and logged.

3. **Capture from rooted older One UI**
   - Objective: capture host→dock control transfers on the rooted Galaxy S9 while reproducing the stock silent-fan path, then compare that trace against the stock Note 9 fan-positive baseline.
   - Acceptance: sequence of control transfers captured cleanly enough to compare dock setup traffic, fan-related divergence, or the absence of expected vendor traffic.

4. **Replay test (software)**
   - Objective: replay discovered sequence from test app and observe fan behavior.
   - Acceptance: fan speed changes reproducibly and safely.

5. **Temperature safety**
   - Objective: verify phone temperature remains within safe limits during tests.
   - Acceptance: phone temperature does not exceed defined threshold; app halts if threshold reached.

6. **Hardware bypass verification**
   - Objective: if software route fails, implement MCU bypass and verify control.
   - Acceptance: MCU reliably drives fan at multiple speeds and responds to phone commands.

## Rooted Galaxy S9 capture runbook

Use this runbook only if the Galaxy S9 `SM-G960F` remains stable after the verified OEM-unlock step and the test operator accepts the rooting risk. Keep the Note 9 as a stock fan-positive baseline for comparison, not as the primary rooted target.

### Preconditions

- Record the S9 exact model / region, Android version, One UI version, boot-state, and current backup state in `docs/HARDWARE_INVENTORY.md`.
- Confirm the dock still reaches the currently known S9 behavior before spending time on capture: charge works, HDMI works, DeX launches, fan stays silent.
- Keep one short stock Note 9 dock session available as the comparison baseline because that is still the only locally verified fan-positive phone.
- Complete `docs/S9_PRE_FLASH_CHECKLIST.md` and `docs/S9_BACKUP_AND_RECOVERY_CHECKLIST.md` before any recovery or root step.
- Do not commit raw rooted-phone captures directly to the repository; review and redact them first.

### 1. Confirm rooted capture viability

Run these commands after root to confirm `usbmon` is usable on the device.

```bash
adb devices
adb shell getprop ro.product.model
adb shell getprop ro.build.version.release
adb shell getprop ro.build.display.id
adb shell su -c 'mount -t debugfs none /sys/kernel/debug || true'
adb shell su -c 'ls /sys/kernel/debug/usb/usbmon'
adb shell su -c 'test -e /sys/kernel/debug/usb/usbmon/0u && echo usbmon_ready'
```

If `usbmon_ready` does not appear, stop and reassess before attempting live capture.

### 2. Capture baseline state before the dock event

Create a local artifact folder outside the repository or in an ignored local path, then collect pre-capture state.

```bash
adb shell dumpsys usb > s9_dumpsys_usb_before.txt
adb shell getprop > s9_getprop_before.txt
adb logcat -c
```

Record the charger, cable, dock used, whether the case is removed, and the exact wall-clock start time. Use `s9_` filenames for new capture artifacts to avoid mixing them with the older Note 9 baseline.

### 3. Start synchronized capture

Start logcat on the host and `usbmon` on the rooted S9 before seating the phone in the dock.

```bash
adb logcat -v threadtime > s9_logcat_threadtime.txt
adb shell su -c 'sh -c "cat /sys/kernel/debug/usb/usbmon/0u > /data/local/tmp/s9_dex_usbmon.txt & echo \$! > /data/local/tmp/s9_usbmon.pid"'
```

If `0u` is too noisy, repeat later with the specific USB bus file (`1u`, `2u`, and so on) after identifying the correct bus.

### 4. Perform a minimal dock event sequence

With capture running, do only one short, well-timed sequence:

1. Seat the rooted S9 in the dock with the known-good charger and cable.
2. Wait for DeX to initialize.
3. Observe and note fan behavior, charging state, HDMI behavior, any UI transitions, and whether the rooted trace shows setup traffic that was not visible from stock Android.
4. Leave the setup stable for a short idle period.
5. Disconnect the phone cleanly.

Write down timestamps for connect, DeX start, any fan speed change, and disconnect so they can be aligned with `usbmon` later. If the S9 still leaves the fan silent, keep that run as the primary rooted silent-path trace and compare it later against the stock Note 9 fan-positive timing notes.

### 5. Stop capture and pull artifacts

```bash
adb shell su -c 'kill $(cat /data/local/tmp/s9_usbmon.pid)'
adb pull /data/local/tmp/s9_dex_usbmon.txt .
adb shell dumpsys usb > s9_dumpsys_usb_after.txt
```

Keep the raw `s9_dex_usbmon.txt` file local until it has been reviewed.

### 6. Artifact handling and review

- Keep raw `usbmon` and full `logcat` captures out of the repository by default.
- Move only reviewed, redacted, and clearly labeled derivatives into `data/` when they are approved for retention.
- Summarize the run in plain language: phone build, charger, cable, whether the fan changed, and whether the trace appears to contain vendor-specific control transfers.

## Note 9 comparison baseline runbook

Use the Note 9 only as the stock fan-positive comparison path unless a practical root path for the exact local variant is later confirmed.

- Reconfirm that the Note 9 still drives the DeX Station fan in stock form.
- Capture the same human-observed timing notes used for the rooted S9 run: connect, DeX start, fan start or fan change, and disconnect.
- If a later exact-model Note 9 root path becomes practical, add a separate rooted Note 9 runbook rather than replacing the S9 primary path.

## Monitoring and logging

- Log all USB probes and responses to timestamped files in app-internal storage, then copy reviewed artifacts into `/data` when they should be retained in the repository.
- Record thermal readings and current draw during replay tests.

## Acceptance criteria

- Either a reproducible software control sequence is found and implemented, or a documented hardware bypass is validated.
- All tests pass without damage and with documented safety checks.
