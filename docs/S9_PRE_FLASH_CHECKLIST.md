# Galaxy S9 Pre-Flash Checklist

Use this checklist before any Odin, recovery, or Magisk step on the rooted-capture candidate Galaxy S9 `SM-G960F`.

This document is intentionally limited to staging and go/no-go preparation. It does not authorize flashing by itself.

## Scope

- Target device: Samsung Galaxy S9 `SM-G960F`
- Device codename: `starlte`
- Current locally verified build: `QP1A.190711.020.G960FXXUHFVB4`
- Current locally verified boot state: `ro.boot.flash.locked=0`, `ro.boot.verifiedbootstate=orange`

## Exact-Match Gate

- [x] Confirm the phone still reports model `SM-G960F` or `SM-G960F/DS`.
- [ ] Confirm the rooted-target codename is still being treated as `starlte` in all downloaded recovery references.
- [ ] Stop if any file, guide, or package references a different Galaxy S9 family or codename.

## Firmware And State Gate

- [x] Confirm the phone still boots the stock OS successfully.
- [x] Confirm the phone is still on Android 10, which matches the currently documented `starlte` installation prerequisite from the official LineageOS page.
- [x] Confirm charge, HDMI, and DeX still work on the DeX Station before any flash step.
- [x] Confirm wireless or USB `adb` still works.
- [x] Confirm no Google accounts have been added back to the phone.

## Package Staging Gate

- [ ] Stage one trusted Odin package on the Windows machine.
- [ ] Stage the official TWRP Galaxy S9 (Exynos) `starlte` Odin `.tar` package, not an image for another model.
- [ ] Stage the current Magisk APK from the official release source.
- [ ] Stage one full stock firmware package for the exact `SM-G960F` family as a recovery fallback.
- [ ] Keep all staged files outside the repository.
- [ ] Record only package names, versions, and source notes in project docs if needed; do not commit binaries.

## Package Sanity Checks

- [ ] Confirm the TWRP package explicitly targets Galaxy S9 Exynos / `starlte`.
- [ ] Confirm the Magisk artifact is an APK from the official release page.
- [ ] Confirm the fallback stock firmware package matches the exact device family and region expectations closely enough to be a realistic restore path.
- [ ] If any package source looks unclear, stop and verify before proceeding.

## PC And Cable Prep

- [x] Use the known-good Windows laptop already verified for `adb`.
- [x] Use the known-good USB data cable that has already passed S9 data testing.
- [ ] Close unnecessary phone-management tools before starting Odin work.
- [ ] Keep the USB connection direct and simple; avoid adding new hubs or adapters during flashing.
- [ ] Keep the laptop on stable power for the full session.

## Current Verified State

- 2026-05-28: `adb` is currently working over wireless TCP/IP while docked.
- 2026-05-28: latest quick state check returned model `SM-G960F`, Android `10`, battery `status=2`, `level=84`, `temperature=337`.

## Phone Prep

- [ ] Charge the S9 to a comfortable level before any flash step.
- [ ] Keep the SIM tray removed if that reduces the chance of accidental network or account prompts during setup.
- [ ] Keep the device unlocked and screen-responsive before shutdown.
- [ ] Reconfirm the operator is willing to treat this S9 as a sacrificial research device if recovery is needed.

## Download Mode And Recovery Prep

- [ ] Rehearse the Samsung boot-mode key combos before opening Odin.
- [ ] Download Mode combo prepared: `Volume Down + Bixby + Power` from power-off.
- [ ] Recovery combo prepared: `Volume Up + Bixby + Power` from power-off.
- [ ] Rehearse the no-mistakes handoff: after any recovery flash, the first intended boot target is recovery, not normal system boot.
- [ ] Keep the dm-verity / stock-recovery-replacement warning in mind: if recovery is flashed and the phone boots straight back to stock system first, custom recovery may be replaced.

## Session Logging Prep

- [ ] Create a dated local working folder outside the repository for screenshots, package notes, and recovery logs.
- [ ] Write down the exact wall-clock session start time.
- [ ] Record which cable, laptop, and dock state were used immediately before flashing.
- [ ] Keep a plain-text notepad open for the exact sequence of actions and observed results.

## Final Go / No-Go Questions

- [ ] Do the exact model, codename, and Android-version prerequisites still match?
- [ ] Are Odin, TWRP `starlte`, Magisk APK, and fallback stock firmware all staged?
- [ ] Is the backup / recovery checklist complete?
- [ ] Is the operator prepared to stop immediately if any package, mode, or model reference looks wrong?

If any answer above is `no`, do not proceed to recovery or root.