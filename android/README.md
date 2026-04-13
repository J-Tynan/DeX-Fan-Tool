# Android App Skeleton

This folder contains a Kotlin Android app skeleton for enumerating USB devices, logging safe probe results, and later replaying validated control sequences.

## Current Status
- Gradle files are scaffolded.
- The wrapper script is a placeholder and should be replaced by running `gradle wrapper` from this directory.
- The app starts a minimal activity so the project opens cleanly in Android Studio after wrapper generation.

## Next Steps
1. Install a local Gradle distribution.
2. Run `gradle wrapper` inside `android/`.
3. Open the folder in Android Studio and sync the project.
4. Add USB host permissions and probe logic.
