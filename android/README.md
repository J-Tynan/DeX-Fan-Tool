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
4. Add safe probe scaffolding and logging after the visibility test passes.
