# Hardware Inventory

Track the hardware available for safe experiments before changing any fan-control behavior. Update this file before the first live USB enumeration test and whenever the test bench changes.

## Quick Fill Checklist

- [ ] Fill device details for the primary modern phone (Device, Android/One UI version, USB-C data verified, DeX visibility, Safe for experiments) — see the Phones table below.
- [ ] Fill device details for the older One UI phone (if available).
- [ ] Record the specific model/part number and any notes for the Powered USB hub.
- [ ] Add bench power supply make/model and the planned current limit for tests.
- [ ] Record multimeter and thermal sensor models and where they will be placed during tests.
- [ ] If available, add the USB protocol analyzer model and preferred capture method.

## Phones

| Device | Android / One UI version | USB-C data verified | DeX Station visibility tested | Safe for experiments | Notes |
| --- | --- | --- | --- | --- | --- |
| Samsung Galaxy S23 Ultra | One UI 4.1 based on Android 12 | TBD | Yes | TBD | Record the primary modern test phone first. |
| Samsung Galaxy Note 9 | One UI 8 based on Android 16 | TBD | TBD | Yes | Record any older One UI device that may still drive the fan. |

## Docks

| Dock | Model | Condition | Fan currently works | Opened / modified | Notes |
| --- | --- | --- | --- | --- | --- |
| Primary dock | EE-MG950 | Working | Yes | No | Use this row for the first software-only path. |
| Spare dock | EE-MG950 | Working | Yes | No | Prefer spare hardware for higher-risk tests. |

## Test Bench

| Item | Available | Notes |
| --- | --- | --- |
| Powered USB hub | TBD | Required for early USB tests. |
| Bench power supply | TBD | Add current limit details when available. |
| Multimeter | TBD | Keep at the bench during all live tests. |
| Thermal sensor | TBD | Recommended for replay and stress tests. |
| USB protocol analyzer | TBD | Optional, but useful if Android visibility fails. |

## First Safe Test Path

- Primary modern phone: Samsung Galaxy S23 Ultra
- Older One UI phone (if available): Samsung Galaxy Note 9
- Dock selected for first USB enumeration: Primary dock (model: EE-MG950)
- Powered hub selected: TBD
- Abort path verified: bench power switch / unplug USB / app remains read-only

## Notes

- Keep destructive or invasive tests off the primary dock until the owner signs off.
- Record the date of the last successful USB enumeration test in this file once the Android app can see the dock.
