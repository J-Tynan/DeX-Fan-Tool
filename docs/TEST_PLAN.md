# Test Plan

## Test environment

- **Dock**: Samsung DeX Station EE‑MG950
- **Phones**: one older One UI device (Samsung Galaxy Note 9) that still runs the fan (if available) and one modern test device (Samsung Galaxy S23 Ultra)
- **Tools**: powered USB hub (DeX Station), USB‑C cables, multimeter, bench power supply, optional USB protocol analyzer, optional oscilloscope
- **Software**: Android app (Kotlin) for probing; adb and logcat for logs

## Test cases

1. **Device enumeration**
   - Objective: app lists dock VID/PID and descriptors.
   - Acceptance: device appears in `UsbManager.getDeviceList()` with vendor/product IDs logged.

2. **Safe IN probe**
   - Objective: perform read-only `controlTransfer` probes and log responses.
   - Acceptance: at least one non-empty response captured and logged.

3. **Capture from older One UI**
   - Objective: capture host→dock control transfers while an older One UI phone triggers the fan.
   - Acceptance: sequence of control transfers correlated with fan speed changes captured.

4. **Replay test (software)**
   - Objective: replay discovered sequence from test app and observe fan behavior.
   - Acceptance: fan speed changes reproducibly and safely.

5. **Temperature safety**
   - Objective: verify phone temperature remains within safe limits during tests.
   - Acceptance: phone temperature does not exceed defined threshold; app halts if threshold reached.

6. **Hardware bypass verification**
   - Objective: if software route fails, implement MCU bypass and verify control.
   - Acceptance: MCU reliably drives fan at multiple speeds and responds to phone commands.

## Monitoring and logging

- Log all USB probes and responses to timestamped files in app-internal storage, then copy reviewed artifacts into `/data` when they should be retained in the repository.
- Record thermal readings and current draw during replay tests.

## Acceptance criteria

- Either a reproducible software control sequence is found and implemented, or a documented hardware bypass is validated.
- All tests pass without damage and with documented safety checks.