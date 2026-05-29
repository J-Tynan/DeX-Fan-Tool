# Galaxy S9 Backup And Recovery Checklist

Use this checklist before any irreversible recovery or root action on the Galaxy S9 `SM-G960F`.

The goal is simple: if the next step fails, the project should still know what state the phone was in, what packages were used, and how to return to a known stock baseline.

## Current Device Record

- [x] Confirm `docs/HARDWARE_INVENTORY.md` already contains the current S9 model, build, dock behavior, and boot-state notes.
- [x] Record whether the phone currently boots cleanly to Android without prompts or warnings beyond the expected unlocked-state screen.
- [x] Record whether wireless `adb` still works before shutdown.
- [x] Record the current battery level before any flash step.

- 2026-05-28 reference state: wireless `adb` active while docked, battery `status=2`, `level=84`, `temperature=337`.
- 2026-05-29 live reference state: USB `adb` recovery succeeded, wireless `adb` was re-enabled the same session, build remained `QP1A.190711.020.G960FXXUHFVB4`, and battery snapshot returned `status=2`, `level=88`, `temperature=303`.

## Data And Artifact Hygiene

- [x] Assume the phone can be wiped again.
- [x] Keep no irreplaceable personal data on this device.
- [x] Keep raw rooted captures, host flash-tool logs, and recovery artifacts in a local folder outside the repository until reviewed.
- [x] Do not commit stock firmware packages, recovery images, or Magisk binaries to the repo.

## Fallback Package Readiness

- [x] Stage one stock firmware restore package for the exact S9 family before any flash attempt.
- [x] Record the package source and version in local notes.
- [x] Confirm the restore package is separate from the custom recovery package.
- [x] Confirm the operator knows which package is the restore path and which package is the experimental path.

- 2026-05-29 fallback firmware package verified on disk: Samfrew `SM-G960F` / `O2U`, PDA `G960FXXUHFVB4`, CSC `G960FOTFHFVB2`, filename `O2U-G960FXXUHFVB4-20220318163601.zip`, size `4295979598` bytes, SHA256 `FC619FD58362C4E662ABE82F16FB233B8B0149BED65A1696A1220E647892DE34`.
- 2026-05-29 archive structure check passed and the restore package is distinct from the staged custom recovery tar `twrp-3.7.0_9-0-starlte.img.tar`.

## Recovery Path Clarity

- [x] Write down the exact power-off boot key combo for Download Mode: `Volume Down + Bixby + Power`.
- [x] Write down the exact power-off boot key combo for Recovery: `Volume Up + Bixby + Power`.
- [x] Record the warning that stock ROM behavior can replace custom recovery on first normal boot.
- [x] Decide in advance that if the first recovery boot does not behave as expected, the next action is to stop and reassess rather than improvising.

## Host-Side Logging

- [x] Create a dated local folder for the session.
- [x] Save a text file in that folder with the device model, build, boot state, and planned package set.
- [x] Save `adb devices -l` output before shutdown.
- [x] Save `adb shell getprop` output before shutdown.
- [x] Save a short battery snapshot before shutdown.

## Stock Baseline Preservation

- [x] Keep the Note 9 available as the stock fan-positive comparison path.
- [x] Keep the S23 Ultra available as the modern stock silent-fan comparison path.
- [x] Record that proceeding past this point will sacrifice the current pristine S9 stock silent-fan baseline, and accept that tradeoff because the rooted capture path is now the primary next information source.

## Abort Conditions

- [x] Stop if the exact model or codename mapping becomes unclear.
- [x] Stop if the fallback stock firmware package is missing.
- [x] Stop if the battery is too low for a safe flash session.
- [x] Stop if the USB connection becomes intermittent.
- [x] Stop if the operator is no longer comfortable sacrificing this S9 for research.

## Completion Gate

- [x] The pre-flash checklist is complete.
- [x] The fallback stock firmware package is staged.
- [x] The local logging folder exists.
- [x] Current stock-state facts have been captured.
- [x] The operator agrees that the next step may require recovery work.

If any item above is incomplete, do not proceed to recovery or root.