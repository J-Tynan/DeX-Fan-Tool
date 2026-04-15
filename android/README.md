# Android App Skeleton

This folder contains a Kotlin Android app skeleton for enumerating USB devices, logging safe probe results, and later replaying validated control sequences.

## Current Status

- Gradle files are scaffolded.
- The Gradle wrapper is generated and can be used with `gradlew.bat` on Windows.
- The app builds successfully in debug mode with the generated wrapper.
- The app starts a minimal read-only activity that enumerates visible USB devices without sending control transfers.

## Next Steps

1. Open the folder in Android Studio and sync the project if you want IDE-assisted Android development.
2. Connect the primary modern phone and verify `adb devices` can see it.
3. Install the debug app build and run the USB visibility gate from the TODO.
4. While Samsung DeX is running on the modern phone, keep the app in **read-only visibility mode**: start normal app logging and let the app enumerate visible USB devices, but do not send control transfers and do not try to force fan behavior.
5. Record the visibility-gate result as one of: `no charge`, `charge but no HDMI`, `DeX works but app cannot see dock`, or `full DeX plus app visibility`.
6. If the dock is visible to Android, immediately capture the dock VID/PID and descriptors with the existing enumerator and save the reviewed logs.
7. Do **not** start traffic capture on the modern phone during this gate. Packet capture belongs to the separate rooted Galaxy Note 9 fallback path documented in `docs/TEST_PLAN.md` and should only happen after the modern-phone visibility gate has been classified.
8. Only after the visibility gate passes should you add safe probe scaffolding and later consider read-only IN probing.

## Visibility Gate Decision Rule

- For the primary modern-phone DeX test, the correct action is: **log and enumerate only**.
- Do not attempt to spin up the dock fan deliberately during this stage.
- Do not treat DeX launching by itself as permission to start capture or replay work.
- If DeX works but the app cannot see the dock, record that as a USB visibility failure and stop the Android app workflow there.
- If the app can see the dock, continue with descriptor capture first and keep the session non-destructive.
- If the modern-phone path fails, switch to the rooted Note 9 `usbmon` capture runbook rather than improvising capture logic in the modern-phone app.
