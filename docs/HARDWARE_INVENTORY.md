# Hardware Inventory

Track the hardware available for safe experiments before changing any fan-control behavior. Update this file before the first live USB enumeration test and whenever the test bench changes.

## Quick Fill Checklist

- [x] Fill device details for the primary modern phone (Device, Android/One UI version, USB-C data verified, DeX visibility, Safe for experiments) — see the Phones table below.
- [ ] Fill device details for the older One UI phone (if available).
- [ ] For the Galaxy Note 9, record exact model / region, bootloader unlock status, backup state, and whether root is acceptable for `usbmon` capture.
- [ ] Record the specific model/part number and any notes for the Powered USB hub.
- [ ] Add bench power supply make/model and the planned current limit for tests.
- [ ] Record multimeter and thermal sensor models and where they will be placed during tests.
- [ ] If available, add the USB protocol analyzer model and preferred capture method.

## Phones

| Device | Android / One UI version | USB-C data verified | DeX Station visibility tested | Safe for experiments | Notes |
| --- | --- | --- | --- | --- | --- |
| Samsung Galaxy S23 Ultra (SM-S918B) | Android 16 / One UI 8.0 (`BP2A.250605.031.A3.S918BXXS9EZCI`) | Yes | Yes | Yes (read-only visibility test) | 2026-05-16: wireless `adb` working; phone charges, HDMI works, DeX launches, and the read-only probe app enumerates two USB devices. Widened vendor IN probing later captured non-empty request `0x05` responses from `0x0BDA:0x8152`; focused follow-up probing showed stable all-zero payloads across lengths 8/16/32 and small value/index changes; later boundary sweeps extended device-recipient zero-filled responses to 512 bytes while interface-recipient responses stopped after 64 bytes. Fan remained silent during the session. |
| Samsung Galaxy Note 9 | Likely Android 10 / One UI 2.5 - confirm exact build locally | TBD | TBD | TBD | Older dock-era candidate. First confirm it still drives the fan, then record model / region and whether root plus `usbmon` is realistic. |

## Community Compatibility Matrix

Use this matrix to separate public anecdotes from local verification. Do not promote a row from `Community report` to `Locally verified` until it has been reproduced on project hardware.

| Phone / generation | Community report | Confidence | Likely constraints / caveats | Local status | Notes |
| --- | --- | --- | --- | --- | --- |
| Galaxy S23 / S23 base | Works with `EE-MG950` according to one `r/SamsungDex` report | Low | Remove thick case; fully open-bottom case may be required | Unverified | Treat as a physical-fit and power-path check first. |
| Galaxy S23 Ultra | Mixed reports: one user said it works, another reported no charge and no HDMI | Low | Power brick sensitivity; seating/fit may matter | Full DeX plus app visibility plus characterized read-only IN responses | 2026-05-16 local test: charges, HDMI works, DeX launches, and the app sees `0x04B4:0x5210` plus `0x0BDA:0x8152`. Widened read-only vendor IN probes found non-empty request `0x05` responses on `0x0BDA:0x8152`, focused follow-up probing showed full-length all-zero payloads across the tested length/value/index combinations, and later boundary sweeps extended device-recipient zero-filled responses to 512 bytes while interface-recipient responses stopped after 64 bytes. Fan remained silent throughout. |
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

- Keep destructive or invasive tests off the primary dock until a go/no-go decision is recorded.
- Last successful USB enumeration test: 2026-05-16 on Samsung Galaxy S23 Ultra (SM-S918B), Android 16 / One UI 8.0 build `BP2A.250605.031.A3.S918BXXS9EZCI`.
- Visible USB functions from the 2026-05-16 read-only probe log: `0x04B4:0x5210` and `0x0BDA:0x8152`.
- Read-only probe expansion result from 2026-05-16: `0x0BDA:0x8152` returned full-length all-zero payloads for vendor IN request `0x05` with request types `0xC0` and `0xC1` through length `64`; repeated runs then showed that device-recipient `0xC0` responses continued full-length and all-zero at `128`, `256`, `512`, `1024`, and `2048`, while interface-recipient `0xC1` attempts reproducibly did not respond beyond `64`. `0x04B4:0x5210` returned no data in the tested request window.
- Keep raw rooted-phone captures out of the repository until they have been reviewed and redacted; only approved extracts belong in `/data`.
- Community survey source: `docs/COMMUNITY_REFERENCES.md`
