# Hardware Inventory

Track the hardware available for safe experiments before changing any fan-control behavior. Update this file before the first live USB enumeration test and whenever the test bench changes.

## Quick Fill Checklist

- [x] Fill device details for the primary modern phone (Device, Android/One UI version, USB-C data verified, DeX visibility, Safe for experiments) — see the Phones table below.
- [x] Fill device details for the older One UI phone (if available).
- [ ] For the Galaxy Note 9, record exact model / region, bootloader unlock status, backup state, and whether root is acceptable for `usbmon` capture.
- [ ] Record the specific model/part number and any notes for the Powered USB hub.
- [ ] Add bench power supply make/model and the planned current limit for tests.
- [ ] Record multimeter and thermal sensor models and where they will be placed during tests.
- [ ] If available, add the USB protocol analyzer model and preferred capture method.

## Phones

| Device | Android / One UI version | USB-C data verified | DeX Station visibility tested | Safe for experiments | Notes |
| --- | --- | --- | --- | --- | --- |
| Samsung Galaxy S23 Ultra (SM-S918B) | Android 16 / One UI 8.0 (`BP2A.250605.031.A3.S918BXXS9EZCI`) | Yes | Yes | Yes (read-only visibility test) | 2026-05-16: wireless `adb` working; phone charges, HDMI works, DeX launches, and the read-only probe app enumerates two USB devices. Widened vendor IN probing later captured non-empty request `0x05` responses from `0x0BDA:0x8152`; focused follow-up probing showed stable all-zero payloads across lengths 8/16/32 and small value/index changes; later boundary sweeps extended device-recipient zero-filled responses to 512 bytes while interface-recipient responses stopped after 64 bytes. Fan remained silent during the session. |
| Samsung Galaxy S9 (SM-G960F) | Android 10 / One UI 2.5 (`QP1A.190711.020.G960FXXUHFVB4`) | Yes | Yes | Yes (stock dock path and adb confirmed) | 2026-05-28: arrival intake confirmed model `SM-G960F`, stable charging, working USB data, and `adb` visibility as `product=starltexx` / `device=starlte`. Initial boot-state signals showed `ro.boot.flash.locked=1`, `ro.boot.verifiedbootstate=green`, and warranty bit `0`, which matched a locked stock device before any unlock flow. Direct Developer options inspection then confirmed that `OEM unlocking` was visible, and enabling it triggered the expected wipe / reboot flow. After reboot and re-enabling USB debugging, `adb` reconnected successfully and the boot-state signals changed to `ro.boot.flash.locked=0` and `ro.boot.verifiedbootstate=orange`, while warranty bit still read `0`. Stock dock retest on the same day confirmed that the phone charges in the DeX Station, HDMI output works, and DeX launches, but the dock fan still does not spin. Legacy Android 10 wireless `adb` was then enabled successfully over TCP/IP, confirming that cable-free debugging is available for later dock-side capture work without persisting the private LAN address in this repository. A follow-up wireless-only check while the phone was sitting in the DeX Station also succeeded, with the device reachable over `ip:5555` and charging at `34.0 C` (`status=2`, `level=77`, `temperature=340`). |
| Samsung Galaxy Note 9 (SM-N960U1) | Android 10 / One UI 2.5? (`QP1A.190711.020.N960U1UEU9FVG2`) | Yes | Yes | Yes (stock adb and DeX/fan baseline) | 2026-05-16: device powers on, runs DeX, and triggers the DeX Station fan. USB adb works, and the older Android 10 `adb tcpip` flow was enabled successfully for wireless adb. Stock read-only probing later saw the same two USB devices as the S23 Ultra and the same zero-filled `0x0BDA:0x8152` request `0x05` responses. Root / `usbmon` viability currently looks poor: `ro.boot.flash.locked=1`, `sys.oem_unlock_allowed=0`, warranty bit still `0`, and the public Snapdragon TWRP target appears to be `crownqltechn`, not this `crownqlteue` U1 build. |

## Community Compatibility Matrix

Use this matrix to separate public anecdotes from local verification. Do not promote a row from `Community report` to `Locally verified` until it has been reproduced on project hardware.

| Phone / generation | Community report | Confidence | Likely constraints / caveats | Local status | Notes |
| --- | --- | --- | --- | --- | --- |
| Galaxy S23 / S23 base | Works with `EE-MG950` according to one `r/SamsungDex` report | Low | Remove thick case; fully open-bottom case may be required | Unverified | Treat as a physical-fit and power-path check first. |
| Galaxy S23 Ultra | Mixed reports: one user said it works, another reported no charge and no HDMI | Low | Power brick sensitivity; seating/fit may matter | Full DeX plus app visibility plus characterized read-only IN responses | 2026-05-16 local test: charges, HDMI works, DeX launches, and the app sees `0x04B4:0x5210` plus `0x0BDA:0x8152`. Widened read-only vendor IN probes found non-empty request `0x05` responses on `0x0BDA:0x8152`, focused follow-up probing showed full-length all-zero payloads across the tested length/value/index combinations, and later boundary sweeps extended device-recipient zero-filled responses to 512 bytes while interface-recipient responses stopped after 64 bytes. Fan remained silent throughout. |
| Galaxy S24 family | Samsung Community thread title claims the dock works | Low | Page could not be fully extracted; likely still worth trying with minimal accessories first | Unverified | Bookmark as a lead, not proof. |
| Note 20 generation | Community reports indicate the dock fits and should work | Low | Charger choice still discussed; dock was not designed for this generation | Unverified | Useful if one is available as a middle-generation comparison. |
| S8 / Note8 / S9 / Note9 generation | Officially aligned with the original dock era; community treats these as the native generation | Medium | Original dock assumptions most likely hold here | Note 9 locally verified for DeX/fan baseline | Local Note 9 (`SM-N960U1`) runs DeX, triggers the fan, and still shows the same read-only USB device set and zero-filled Realtek IN responses seen on the S23 Ultra. |

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
- Older One UI phone (if available): Samsung Galaxy S9 (`SM-G960F`)
- Preferred fallback capture path if the modern phone cannot see the dock: rooted Note 9 plus `usbmon`, then hardware analyzer only if `usbmon` is unavailable
- Dock selected for first USB enumeration: Primary dock (model: EE-MG950)
- Powered hub selected: TBD
- Abort path verified: bench power switch / unplug USB / app remains read-only
- First live-session checklist: `docs/FIRST_LIVE_DOCK_CHECKLIST.md`
- Incoming replacement-phone checklist: `docs/REPLACEMENT_PHONE_ARRIVAL_CHECKLIST.md`

## Notes

- Keep destructive or invasive tests off the primary dock until a go/no-go decision is recorded.
- Last successful USB enumeration test: 2026-05-16 on Samsung Galaxy S23 Ultra (SM-S918B), Android 16 / One UI 8.0 build `BP2A.250605.031.A3.S918BXXS9EZCI`.
- Visible USB functions from the 2026-05-16 read-only probe log: `0x04B4:0x5210` and `0x0BDA:0x8152`.
- Read-only probe expansion result from 2026-05-16: `0x0BDA:0x8152` returned full-length all-zero payloads for vendor IN request `0x05` with request types `0xC0` and `0xC1` through length `64`; repeated runs then showed that device-recipient `0xC0` responses continued full-length and all-zero at `128`, `256`, `512`, `1024`, and `2048`, while interface-recipient `0xC1` attempts reproducibly did not respond beyond `64`. `0x04B4:0x5210` returned no data in the tested request window.
- Galaxy Note 9 fallback baseline confirmed on 2026-05-16: model `SM-N960U1`, Android 10 build `QP1A.190711.020.N960U1UEU9FVG2`, stock DeX works, the DeX Station fan triggers, USB adb works, and wireless adb is available via the legacy Android 10 TCP/IP ADB flow.
- Galaxy Note 9 baseline rechecked on 2026-05-30: attached operator photo again showed model `SM-N960U1`, and a fresh stock dock test confirmed the DeX Station fan still spins with audible speed changes. Treat this as current evidence that the dock hardware remains functional and that the fan-positive baseline is still reproducible.
- Incoming replacement-device note from 2026-05-30: an Exynos Galaxy Note 9 `SM-N960F` was purchased on eBay and is currently expected to arrive between 2026-06-02 and 2026-06-04. Treat it as the next intake target for the fan-positive rooted-capture path.
- Replacement-device update from 2026-05-28: the Galaxy S9 `SM-G960F` has arrived and passed the first stock intake gate for identity, charging, USB data, and `adb` visibility. `OEM unlocking` was visible, the wipe / reboot flow completed, and post-reboot `adb` now reports the expected unlocked-state signals (`ro.boot.flash.locked=0`, `ro.boot.verifiedbootstate=orange`). A stock dock retest then confirmed charge, HDMI, and DeX success, but the dock fan still remained silent.
- Stock comparison note from 2026-05-16: after USB permission was granted, the Note 9 showed the same read-only probe pattern as the S23 Ultra (`0x04B4:0x5210` silent; `0x0BDA:0x8152` request `0x05` returns all-zero payloads with the same `0xC0` / `0xC1` split), despite the Note 9 actually triggering the fan.
- Root-feasibility note for the exact Note 9 on hand: `SM-N960U1` / `N960U1UEU9FVG2` currently reports `ro.boot.flash.locked=1`, `ro.boot.verifiedbootstate=green`, `sys.oem_unlock_allowed=0`, and warranty bit `0`. That combination is consistent with a locked stock state and does not show a straightforward root path for this exact U.S. U1 variant.
- Keep raw rooted-phone captures out of the repository until they have been reviewed and redacted; only approved extracts belong in `/data`.
- Community survey source: `docs/COMMUNITY_REFERENCES.md`
