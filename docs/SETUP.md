# Development Setup

This document covers the local environment needed to work on the Android probe app, helper scripts, and analysis notebooks in this repository.

## Recommended editor

- VS Code for repository-wide editing, documentation, scripts, and notebooks.
- Android Studio for Android SDK management, first Gradle sync, and device-focused Android debugging.

## VS Code extensions

The workspace recommends these extensions:

- `fwcd.kotlin`
- `vscjava.vscode-java-pack`
- `redhat.vscode-xml`
- `ms-python.python`
- `ms-toolsai.jupyter`
- `github.vscode-github-actions`
- `davidanson.vscode-markdownlint`

## Required software

### Android and Java

- JDK 17
- Android Studio Hedgehog or newer, or a standalone Android SDK installation
- Android SDK Platform 34
- Android Build-Tools 34.x
- Android Platform-Tools (`adb`)
- A local Gradle installation only if the wrapper ever needs to be regenerated

Repository versions in use today:

- Android Gradle Plugin: 8.4.2
- Kotlin: 1.9.24
- Gradle distribution target: 8.7
- `compileSdk` / `targetSdk`: 34
- Java target: 17

### Python and notebooks

- Python 3.10 or newer
- `pip`
- Optional: Jupyter support for notebook work

The current helper script `scripts/replay_sequence.py` only uses the Python standard library, so no extra Python packages are required for that script.

## Windows setup

### 1. Install Java 17

Install a JDK 17 distribution and ensure `JAVA_HOME` points to it.

PowerShell check:

```powershell
java -version
```

Expected result: Java 17.x.

### 2. Install Android Studio and SDK components

Install Android Studio, then use SDK Manager to install:

- Android SDK Platform 34
- Android SDK Build-Tools 34.x
- Android SDK Platform-Tools

Make sure `adb` is available from PowerShell, either via `PATH` or by using the full SDK path.

PowerShell check:

```powershell
adb version
```

### 3. Regenerate the wrapper only if needed

The repository now includes a generated Gradle wrapper. In normal use, build with the checked-in wrapper files from inside `android/`.

PowerShell example:

```powershell
Set-Location c:\Projects\DeX-Fan-Tool\android
.\gradlew.bat tasks
```

If the wrapper files ever need to be regenerated, install a local Gradle distribution and run `gradle wrapper` from inside `android/`.

PowerShell example:

```powershell
Set-Location c:\Projects\DeX-Fan-Tool\android
gradle wrapper
```

This should recreate the wrapper files, including `gradlew.bat` and `gradle-wrapper.jar`.

If you prefer, Android Studio can also be used first to import the Android project and help verify SDK and Gradle configuration.

### 4. Install Python

Install Python 3.10+ and confirm it is available:

```powershell
python --version
```

If you plan to use notebooks in VS Code, install the Jupyter extension and select your Python interpreter in the workspace.

## Clone and open the repository

```powershell
Set-Location c:\Projects
git clone https://github.com/J-Tynan/DeX-Fan-Tool.git
Set-Location c:\Projects\DeX-Fan-Tool
code .
```

When VS Code prompts for recommended extensions, install them.

## First project checks

### Android project

With the generated wrapper in place:

```powershell
Set-Location c:\Projects\DeX-Fan-Tool\android
.\gradlew.bat tasks
```

If `gradlew.bat` does not exist yet, the wrapper generation step did not complete.

### Python helper script

```powershell
Set-Location c:\Projects\DeX-Fan-Tool
python .\scripts\replay_sequence.py .\path\to\sequence.json
```

This is a dry-run preview tool only. It prints a sequence summary before any live replay implementation exists.

### ADB device visibility

```powershell
adb devices
```

For USB-related Android logs, the shell helper in `scripts/capture_usb.sh` is written for POSIX shells. On Windows, either run it from Git Bash or WSL, or use the direct command below in PowerShell:

```powershell
adb logcat -v time UsbHostManager:* UsbDeviceManager:* *:S
```

## Recommended workflow

1. Read `docs/PROJECT_CHARTER.md`, `docs/RISK_AND_SAFETY.md`, and `docs/REVERSE_ENGINEERING_POLICY.md` before touching hardware.
2. Keep probing read-only until the protocol is understood.
3. Use Android Studio when you need full Android project sync or device debugging.
4. Use VS Code for docs, scripts, notebooks, CI files, and quick Kotlin edits.

## Troubleshooting

### `adb` not found

- Add the Android SDK `platform-tools` directory to `PATH`.
- Restart VS Code or your terminal after changing environment variables.

### Gradle wrapper missing or failing

- Confirm `gradle` is installed before running `gradle wrapper`.
- Confirm `JAVA_HOME` points to JDK 17.
- Re-run wrapper generation from `c:\Projects\DeX-Fan-Tool\android`.

### VS Code Kotlin support feels incomplete

- This is expected for some Android-specific workflows.
- Fall back to Android Studio for Gradle sync, manifest/resource editing assistance, and device debugging.

## Safety reminder

This repository is set up for non-destructive exploration first. Use powered USB hardware, monitor thermals and current draw, and do not start sending OUT transfers until the documented safety checks are in place.
