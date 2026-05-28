# Galaxy S9 Backup And Recovery Checklist

Use this checklist before any irreversible recovery or root action on the Galaxy S9 `SM-G960F`.

The goal is simple: if the next step fails, the project should still know what state the phone was in, what packages were used, and how to return to a known stock baseline.

## Current Device Record

- [ ] Confirm `docs/HARDWARE_INVENTORY.md` already contains the current S9 model, build, dock behavior, and boot-state notes.
- [ ] Record whether the phone currently boots cleanly to Android without prompts or warnings beyond the expected unlocked-state screen.
- [ ] Record whether wireless `adb` still works before shutdown.
- [ ] Record the current battery level before any flash step.

## Data And Artifact Hygiene

- [ ] Assume the phone can be wiped again.
- [ ] Keep no irreplaceable personal data on this device.
- [ ] Keep raw rooted captures, Odin logs, and recovery artifacts in a local folder outside the repository until reviewed.
- [ ] Do not commit stock firmware packages, recovery images, or Magisk binaries to the repo.

## Fallback Package Readiness

- [ ] Stage one stock firmware restore package for the exact S9 family before any flash attempt.
- [ ] Record the package source and version in local notes.
- [ ] Confirm the restore package is separate from the custom recovery package.
- [ ] Confirm the operator knows which package is the restore path and which package is the experimental path.

## Recovery Path Clarity

- [ ] Write down the exact power-off boot key combo for Download Mode: `Volume Down + Bixby + Power`.
- [ ] Write down the exact power-off boot key combo for Recovery: `Volume Up + Bixby + Power`.
- [ ] Record the warning that stock ROM behavior can replace custom recovery on first normal boot.
- [ ] Decide in advance that if the first recovery boot does not behave as expected, the next action is to stop and reassess rather than improvising.

## Host-Side Logging

- [ ] Create a dated local folder for the session.
- [ ] Save a text file in that folder with the device model, build, boot state, and planned package set.
- [ ] Save `adb devices -l` output before shutdown.
- [ ] Save `adb shell getprop` output before shutdown.
- [ ] Save a short battery snapshot before shutdown.

## Stock Baseline Preservation

- [ ] Keep the Note 9 available as the stock fan-positive comparison path.
- [ ] Keep the S23 Ultra available as the modern stock silent-fan comparison path.
- [ ] Do not destroy the ability to repeat the current S9 stock silent-fan baseline until the rooted path has yielded something useful.

## Abort Conditions

- [ ] Stop if the exact model or codename mapping becomes unclear.
- [ ] Stop if the fallback stock firmware package is missing.
- [ ] Stop if the battery is too low for a safe flash session.
- [ ] Stop if the USB connection becomes intermittent.
- [ ] Stop if the operator is no longer comfortable sacrificing this S9 for research.

## Completion Gate

- [ ] The pre-flash checklist is complete.
- [ ] The fallback stock firmware package is staged.
- [ ] The local logging folder exists.
- [ ] Current stock-state facts have been captured.
- [ ] The operator agrees that the next step may require recovery work.

If any item above is incomplete, do not proceed to recovery or root.