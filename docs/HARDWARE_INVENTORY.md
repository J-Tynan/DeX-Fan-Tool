# Hardware Inventory

Track the hardware available for safe experiments before changing any fan-control behavior. Update this file before the first live USB enumeration test and whenever the test bench changes.

## Phones

| Device | Android / One UI version | USB-C data verified | DeX Station visibility tested | Safe for experiments | Notes |
| --- | --- | --- | --- | --- | --- |
| TBD | TBD | TBD | TBD | TBD | Record the primary modern test phone first. |
| TBD | TBD | TBD | TBD | TBD | Record any older One UI device that may still drive the fan. |

## Docks

| Dock | Model | Condition | Fan currently works | Opened / modified | Notes |
| --- | --- | --- | --- | --- | --- |
| Primary dock | EE-MG950 | TBD | TBD | No | Use this row for the first software-only path. |
| Spare dock | EE-MG950 | TBD | TBD | No | Prefer spare hardware for higher-risk tests. |

## Test Bench

| Item | Available | Notes |
| --- | --- | --- |
| Powered USB hub | TBD | Required for early USB tests. |
| Bench power supply | TBD | Add current limit details when available. |
| Multimeter | TBD | Keep at the bench during all live tests. |
| Thermal sensor | TBD | Recommended for replay and stress tests. |
| USB protocol analyzer | TBD | Optional, but useful if Android visibility fails. |

## First Safe Test Path

- Primary modern phone: TBD
- Older One UI phone (if available): TBD
- Dock selected for first USB enumeration: TBD
- Powered hub selected: TBD
- Abort path verified: bench power switch / unplug USB / app remains read-only

## Notes

- Keep destructive or invasive tests off the primary dock until the owner signs off.
- Record the date of the last successful USB enumeration test in this file once the Android app can see the dock.
