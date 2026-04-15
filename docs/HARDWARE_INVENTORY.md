# Hardware Inventory

Track the hardware available for safe experiments before changing any fan-control behavior. Update this file before the first live USB enumeration test and whenever the test bench changes.

## Quick Fill Checklist

- [ ] Fill device details for the primary modern phone (Device, Android/One UI version, USB-C data verified, DeX visibility, Safe for experiments) — see the Phones table below.
- [ ] Fill device details for the older One UI phone (if available).
- [ ] For the Galaxy Note 9, record exact model / region, bootloader unlock status, backup state, and whether root is acceptable for `usbmon` capture.
- [ ] Record the specific model/part number and any notes for the Powered USB hub.
- [ ] Add bench power supply make/model and the planned current limit for tests.
- [ ] Record multimeter and thermal sensor models and where they will be placed during tests.
- [ ] If available, add the USB protocol analyzer model and preferred capture method.

## Phones

| Device | Android / One UI version | USB-C data verified | DeX Station visibility tested | Safe for experiments | Notes |
| --- | --- | --- | --- | --- | --- |
| Samsung Galaxy S23 Ultra | TBD - record actual Android / One UI build locally | TBD | TBD | TBD | Replace the placeholder OS information with the real device build before live testing. |
| Samsung Galaxy Note 9 | Likely Android 10 / One UI 2.5 - confirm exact build locally | TBD | TBD | TBD | Older dock-era candidate. First confirm it still drives the fan, then record model / region and whether root plus `usbmon` is realistic. |

## Community Compatibility Matrix

Use this matrix to separate public anecdotes from local verification. Do not promote a row from `Community report` to `Locally verified` until it has been reproduced on project hardware.

| Phone / generation | Community report | Confidence | Likely constraints / caveats | Local status | Notes |
| --- | --- | --- | --- | --- | --- |
| Galaxy S23 / S23 base | Works with `EE-MG950` according to one `r/SamsungDex` report | Low | Remove thick case; fully open-bottom case may be required | Unverified | Treat as a physical-fit and power-path check first. |
| Galaxy S23 Ultra | Mixed reports: one user said it works, another reported no charge and no HDMI | Low | Power brick sensitivity; seating/fit may matter | Unverified | Important decision-gate target for this project. |
| Galaxy S24 family | Samsung Community thread title claims the dock works | Low | Page could not be fully extracted; likely still worth trying with minimal accessories first | Unverified | Bookmark as a lead, not proof. |
| Note 20 generation | Community reports indicate the dock fits and should work | Low | Charger choice still discussed; dock was not designed for this generation | Unverified | Useful if one is available as a middle-generation comparison. |
| S8 / Note8 / S9 / Note9 generation | Officially aligned with the original dock era; community treats these as the native generation | Medium | Original dock assumptions most likely hold here | Unverified in repo | Best candidates for fallback capture and baseline fan behavior. |

## Community-Informed Test Constraints

- Use a known-good adaptive fast charger and cable for early dock tests.
- Test once with the phone case removed if the connector fit is even slightly questionable.
- Record `no charge`, `charge but no HDMI`, `HDMI works but no dock visibility`, and `full DeX works` as separate outcomes.
- Treat any community compatibility claim as anecdotal until it is reproduced locally.

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
- Preferred fallback capture path if the modern phone cannot see the dock: rooted Note 9 plus `usbmon`, then hardware analyzer only if `usbmon` is unavailable
- Dock selected for first USB enumeration: Primary dock (model: EE-MG950)
- Powered hub selected: TBD
- Abort path verified: bench power switch / unplug USB / app remains read-only
- First live-session checklist: `docs/FIRST_LIVE_DOCK_CHECKLIST.md`

## Notes

- Keep destructive or invasive tests off the primary dock until the owner signs off.
- Record the date of the last successful USB enumeration test in this file once the Android app can see the dock.
- Keep raw rooted-phone captures out of the repository until they have been reviewed and redacted; only approved extracts belong in `/data`.
- Community survey source: `docs/COMMUNITY_REFERENCES.md`
