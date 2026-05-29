# Replacement Phone Arrival Checklist

Use this checklist when the ordered replacement phone arrives. The current target is the incoming Galaxy S9 `SM-G960F`, but the checklist is written so it can be reused for later replacement devices.

The goal is to avoid wasting time on root steps before basic intake is complete. A phone with the right model number can still be a bad project candidate if the USB-C port is damaged, DeX output is unstable, or `OEM unlocking` is missing.

## Before The Parcel Arrives

- [x] Confirm `adb` works on the Windows machine that will be used for intake.
- [x] Confirm one known-good USB data cable is set aside for the phone.
- [x] Confirm the DeX Station, power supply, HDMI path, and display are ready for a short stock test.
- [ ] Download but do not flash the likely root-work tools yet: Heimdall or Odin, the relevant TWRP package for `starlte`, and current Magisk files.
- [x] Make sure `docs/HARDWARE_INVENTORY.md` already lists the incoming phone.
- [x] Decide in advance that no Google or Samsung account will be added during intake.

## Parcel And Physical Intake

- [ ] Photograph the parcel condition before opening if it looks damaged.
- [ ] Photograph the phone front, back, frame corners, and USB-C port.
- [x] Confirm the phone is physically a Galaxy S9-class device and not a substituted model.
- [x] Check for obvious display cracks, frame bends, battery swelling, or water-damage signs.
- [x] Check whether a SIM tray is present and whether the buttons feel normal.

## Identity Check

- [x] Confirm the exact model number from the label or the booted OS.
- [x] Confirm the model is `SM-G960F` or `SM-G960F/DS`.
- [x] If the phone is `U`, `U1`, or another unexpected variant, stop and record that immediately.
- [ ] Record the IMEI status only as `clear`, `unclear`, or `problem`; do not commit the full IMEI.

## First Boot Rules

- [x] Boot the phone on stock firmware before attempting any flashing.
- [x] Do not sign into Google or Samsung accounts.
- [x] Confirm the phone reaches the home screen or setup flow normally.
- [x] Confirm the device is factory reset and not blocked by Google lock, MDM, or another activation problem.
- [x] Record the Android version, One UI version, and build number in `docs/HARDWARE_INVENTORY.md`.

## USB-C And Charging Check

- [x] Confirm the phone charges normally from a known-good charger.
- [x] Confirm the USB-C port holds the cable securely without obvious wobble.
- [x] Confirm PC USB data works, not just charging.
- [ ] If the USB-C connection is intermittent, treat that as a serious blocker for this project.

## Samsung DeX Dock Check

- [x] Test the phone in the `EE-MG950` dock on stock firmware first.
- [x] Record whether the phone charges in the dock.
- [x] Record whether HDMI output appears.
- [x] Record whether Samsung DeX launches.
- [x] Record whether the dock fan spins.
- [x] If DeX works, note whether the behavior matches dock-era expectations better than the S23 Ultra.

## Bootloader And Root Readiness Check

- [x] Enable Developer options.
- [x] Check whether `OEM unlocking` is visible.
- [x] Check whether USB debugging can be enabled normally.
- [ ] If `OEM unlocking` is missing, do not assume the phone is useless; record the exact state first.
- [ ] Do not flash anything yet unless the full stock intake above is complete.

## Minimum Pass Criteria

Treat the device as a good project candidate only if all of these are true.

- [x] Exact model is `SM-G960F` or `SM-G960F/DS`.
- [x] The phone boots and is not account-locked.
- [x] USB-C charging and data are stable.
- [x] The screen is usable enough for setup and testing.
- [x] The DeX dock path is at least testable.
- [x] `OEM unlocking` is present or at least not obviously ruled out.

## Stop Conditions

- [ ] Wrong model or wrong region variant.
- [ ] Google lock, MDM lock, or other activation blocker.
- [ ] USB-C port is charge-only, intermittent, or physically damaged.
- [ ] Severe overheating, battery swelling, or unstable power behavior.
- [ ] Display damage so severe that setup or Developer options work is impractical.

## Post-Intake Updates

- [x] Update `docs/HARDWARE_INVENTORY.md` with the real device state.
- [x] Update the procurement task in `TODO.md` from in-progress to done if the device passes intake.
- [ ] If the phone fails intake, record the reason and return to `docs/DEX_PHONE_SHORTLIST.md`.
- [x] If the phone passes intake, schedule the root-readiness and stock comparison session next.

## Current State Note

- 2026-05-28: enabling `OEM unlocking` on the Galaxy S9 showed a `Delete all` confirmation and started a wipe / reboot flow. After reboot, Developer options and USB debugging were re-enabled, `adb` reconnected normally, and the boot-state signals changed to `ro.boot.flash.locked=0` and `ro.boot.verifiedbootstate=orange`. A same-day stock dock retest then confirmed charge, HDMI, and DeX success, but the dock fan still stayed silent. Legacy Android 10 wireless `adb` was then enabled successfully over TCP/IP for later dock-side capture convenience, without recording the private Wi-Fi address in the repo. Treat the bootloader path as confirmed enough to proceed with later rooted comparison work, not flashing yet.

## Before Any Recovery Or Root Session

- Complete `docs/S9_PRE_FLASH_CHECKLIST.md`.
- Complete `docs/S9_BACKUP_AND_RECOVERY_CHECKLIST.md`.
- Do not treat arrival intake alone as enough preparation for a recovery flash.

## Immediate Next Session After A Pass

1. Re-run the stock DeX / dock comparison against the Note 9 and S23 Ultra baselines.
2. Confirm `adb` visibility and capture the stock device details cleanly.
3. Decide whether the phone is strong enough to risk the root / `usbmon` path.