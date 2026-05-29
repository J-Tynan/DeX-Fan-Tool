# Galaxy S9 Pre-Flash Checklist

Use this checklist before any host-side flash-tool, recovery, or Magisk step on the rooted-capture candidate Galaxy S9 `SM-G960F`.

This document is intentionally limited to staging and go/no-go preparation. It does not authorize flashing by itself.

## Scope

- Target device: Samsung Galaxy S9 `SM-G960F`
- Device codename: `starlte`
- Current locally verified build: `QP1A.190711.020.G960FXXUHFVB4`
- Current locally verified boot state: `ro.boot.flash.locked=0`, `ro.boot.verifiedbootstate=orange`

## Exact-Match Gate

- [x] Confirm the phone still reports model `SM-G960F` or `SM-G960F/DS`.
- [x] Confirm the rooted-target codename is still being treated as `starlte` in all downloaded recovery references.
- [x] Confirm no file, guide, or package in the selected flash set references a different Galaxy S9 family or codename.

## Firmware And State Gate

- [x] Confirm the phone still boots the stock OS successfully.
- [x] Confirm the phone is still on Android 10, which matches the currently documented `starlte` installation prerequisite from the official LineageOS page.
- [x] Confirm charge, HDMI, and DeX still work on the DeX Station before any flash step.
- [x] Confirm wireless or USB `adb` still works.
- [x] Confirm no Google accounts have been added back to the phone.

## Package Staging Gate

- [x] Stage one trusted host flash tool on the Windows machine.
- [x] If using Heimdall, stage the official Windows Heimdall package from the project site.
- [x] Record that Odin is not staged for this session because Heimdall is the selected host flash tool and no fallback switch is planned unless Heimdall fails.
- [x] Stage the official TWRP Galaxy S9 (Exynos) `starlte` recovery package appropriate for the selected host flash tool, not an image for another model.
- [x] Stage the current Magisk APK from the official release source.
- [x] Stage one full stock firmware package for the exact `SM-G960F` family as a recovery fallback.
- [x] Keep all staged files outside the repository.
- [x] Record only package names, versions, and source notes in project docs if needed; do not commit binaries.

## Package Sanity Checks

- [x] Confirm the TWRP package explicitly targets Galaxy S9 Exynos / `starlte`.
- [x] Confirm the Magisk artifact is an APK from the official release page.
- [x] Confirm the fallback stock firmware package target matches the exact device family and region expectations closely enough to be a realistic restore path.
- [x] Confirm the currently selected package sources are clear enough to proceed; if that changes, stop and verify before proceeding.

## PC And Cable Prep

- [x] Use the known-good Windows laptop already verified for `adb`.
- [x] Use the known-good USB data cable that has already passed S9 data testing.
- [x] Confirm the required Microsoft Visual C++ runtime for the bundled Heimdall tools is satisfied on this machine.
- [x] Close unnecessary phone-management tools before starting Heimdall or Odin work.
- [x] Keep the USB connection direct and simple; avoid adding new hubs or adapters during flashing.
- [x] Keep the laptop on stable power for the full session.

## Current Verified State

- 2026-05-28: `adb` is currently working over wireless TCP/IP while docked.
- 2026-05-28: latest quick state check returned model `SM-G960F`, Android `10`, battery `status=2`, `level=84`, `temperature=337`.
- 2026-05-28: official Windows Heimdall package `heimdall-suite-1.4.0-win32.zip` was downloaded from the official project-linked Bitbucket URL and staged locally.
- 2026-05-28: extracted Heimdall tools include `heimdall.exe`, `heimdall-frontend.exe`, bundled `zadig.exe`, and `README.txt` in the local staging folder.
- 2026-05-28: `Magisk-v30.7.apk` was staged locally and its SHA256 was recorded during verification.
- 2026-05-29: Microsoft Visual C++ 2010 x86 and x64 redistributables were installed on the Windows laptop because the bundled Heimdall README points to that runtime.
- 2026-05-29: Heimdall CLI launch validation later showed an additional Visual C++ 2012 x86 dependency (`MSVCP110.dll`, `MSVCR110.dll`), so the Microsoft Visual C++ 2012 x86 redistributable was also installed and `heimdall.exe version` then returned `v1.4.0` successfully.
- 2026-05-29: the staged flash package set now lives in `%USERPROFILE%\Downloads\DeX-Fan-Tool-S9-pre-root` outside the repository.
- 2026-05-29: exact fallback firmware package `O2U-G960FXXUHFVB4-20220318163601.zip` is staged in the external prep folder for `SM-G960F` / `O2U` / Android `10` with PDA `G960FXXUHFVB4` and CSC `G960FOTFHFVB2`.
- 2026-05-29: firmware zip verification recorded size `4295979598` bytes, SHA256 `FC619FD58362C4E662ABE82F16FB233B8B0149BED65A1696A1220E647892DE34`, and a readable 5-part Samsung payload (`BL`, `AP`, `CP`, `HOME_CSC`, `CSC`).
- 2026-05-29: verified custom recovery tar `twrp-3.7.0_9-0-starlte.img.tar` is also staged in the same external prep folder.
- 2026-05-29: live preflash reconnect over USB succeeded, wireless `adb` over TCP/IP was restored, and the current device check again returned model `SM-G960F`, build `QP1A.190711.020.G960FXXUHFVB4`, `ro.boot.flash.locked=0`, and `ro.boot.verifiedbootstate=orange`.
- 2026-05-29: fresh battery snapshot returned `status=2`, `level=88`, `temperature=303`, and the saved `adb devices -l`, `getprop`, and battery outputs were written to the external preflash session folder.
- 2026-05-29: operator confirmed a direct USB flash path from this Windows 11 laptop to the S9 with no hubs or adapters, stable laptop power for the session, and unnecessary phone-management app windows closed before Heimdall work.

## Phone Prep

- [x] Charge the S9 to a comfortable level before any flash step.
- [x] Record the SIM tray state for the session and remove it if desired to reduce accidental network or account prompts during setup.
- [x] Keep the device unlocked and screen-responsive before shutdown.
- [x] Reconfirm the operator is willing to treat this S9 as a sacrificial research device if recovery is needed.

## Download Mode And Recovery Prep

- [x] Rehearse the Samsung boot-mode key combos before opening the selected host flash tool.
- [x] Download Mode combo prepared: `Volume Down + Bixby + Power` from power-off.
- [x] Recovery combo prepared: `Volume Up + Bixby + Power` from power-off.
- [x] Rehearse the no-mistakes handoff: after any recovery flash, the first intended boot target is recovery, not normal system boot.
- [x] Keep the dm-verity / stock-recovery-replacement warning in mind: if recovery is flashed and the phone boots straight back to stock system first, custom recovery may be replaced.

## Session Logging Prep

- [x] Create a dated local working folder outside the repository for screenshots, package notes, and recovery logs.
- [x] Write down the exact wall-clock session start time.
- [x] Record which cable, laptop, and dock state were used immediately before flashing.
- [x] Keep a plain-text notepad open for the exact sequence of actions and observed results.

## Final Go / No-Go Questions

- [x] Do the exact model, codename, and Android-version prerequisites still match?
- [x] Are the selected host flash tool, TWRP `starlte`, Magisk APK, and fallback stock firmware all staged?
- [x] Is the backup / recovery checklist complete?
- [x] Is the operator prepared to stop immediately if any package, mode, or model reference looks wrong?

If any answer above is `no`, do not proceed to recovery or root.