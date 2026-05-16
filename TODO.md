# DeX Fan Restore — TODO

**Purpose:** single source of truth for project tasks, priorities, and progress tracking.

---

## How to use this file

- **Status markers:** `- [ ]` = open, `- [~]` = in progress, `- [x]` = done.  
- **Priority:** `P0` (critical), `P1` (high), `P2` (medium), `P3` (low).  
- **Estimate:** rough timebox in hours or days.  
- **Dependencies:** list tasks that must finish first.  
- **Updating:** when you start a task change `- [ ]` → `- [~]`; when complete change to `- [x]` and add a short note with date and link to any related commit or issue.

---

## Current starter tasks

### Planning & governance

- [x] **Project charter** — P0 — *0.5d* — Created `docs/PROJECT_CHARTER.md`.  
- [x] **Scope & non-goals** — P0 — *0.25d* — Created `docs/SCOPE.md`.  
- [x] **Risk & safety plan** — P0 — *0.5d* — Created `docs/RISK_AND_SAFETY.md`.  
- [x] **Repository skeleton** — P1 — *0.5d* — Repo created and initial docs uploaded.  
- [x] **Define license & distribution policy** — P1 — *0.5d* — Completed 2026-04-15 in `LICENSE.md`, `legal/LICENSE.md`, and `legal/DISTRIBUTION_POLICY.md`, including the project-review workflow and pre-publication checklist.  
- [x] **Create CONTRIBUTING.md and CODE_OF_CONDUCT** — P2 — *0.5d* — Implementation started 2026-04-13 in `.github/CONTRIBUTING.md` and `.github/CODE_OF_CONDUCT.md`.

### Research & reconnaissance

- [~] **Inventory available phones and One UI versions** — P0 — *0.5d* — Populate exact device model, Android / One UI version, USB-C data status, and selected first-test hardware in `docs/HARDWARE_INVENTORY.md`.  
- [ ] **Record exact Galaxy Note 9 build and rooted-capture viability** — P0 — *0.25d* — Confirm exact model / region, Android and One UI version, bootloader unlock status, backup state, and whether the phone still triggers DeX Station fan behavior before attempting root.  
- [x] **Verify test bench readiness before first live USB test** — P0 — *0.5d* — Confirm powered hub, smartphones, cables, power path, and abort path before live enumeration.  
- [x] **Verify dock visibility on modern phone (decision gate)** — P0 — *0.5d* — Completed 2026-05-16 on the Galaxy S23 Ultra (`SM-S918B`, Android 16 / One UI 8.0): phone charged, HDMI worked, DeX launched, and the read-only probe app enumerated two USB devices. The fan remained silent during the session.  
- [x] **Confirm dock VID/PID and descriptors** — P0 — *1d* — Completed 2026-05-16 with the read-only enumerator: visible functions `0x04B4:0x5210` and `0x0BDA:0x8152` plus interface summaries preserved in the saved probe log.  
- [x] **Establish baseline One UI / fan-control matrix** — P0 — *0.5d* — Initial local baseline recorded 2026-05-16 for Galaxy S23 Ultra / Android 16 / One UI 8.0: `full DeX plus app visibility`; fan silent during the read-only session.  
- [x] **Locate any public references / community work** — P2 — *1d* — Initial survey documented 2026-04-14 in `docs/COMMUNITY_REFERENCES.md`; findings include mixed S23/S24 compatibility anecdotes, charger/cable sensitivity, and no clear public fan-control reverse-engineering repo.

### Software probe (non‑destructive)

- [x] **Install and configure JDK 17 for Android builds** — P0 — *0.25d* — Installed Eclipse Temurin 17 and set `JAVA_HOME` on 2026-04-13; project config targets Java 17.  
- [x] **Install and configure Android SDK and platform-tools** — P0 — *0.25d* — Configured `ANDROID_SDK_ROOT` / `ANDROID_HOME`, provisioned `sdkmanager`, and verified Platform 34, Build-Tools 34.0.0, and `adb`; re-verified locally on 2026-04-14.  
- [x] **Install local Gradle and generate the Android wrapper** — P0 — *0.25d* — Installed local Gradle 8.7 and generated `gradlew.bat` plus `gradle-wrapper.jar` on 2026-04-13.  
- [x] **Validate Android project sync and debug build** — P0 — *0.25d* — Verified wrapper task listing and successful `assembleDebug` build on 2026-04-13 after enabling AndroidX.  
- [x] **Verify modern phone is visible to adb** — P0 — *0.25d* — Completed 2026-05-16 via wireless `adb`; `adb devices -l` reports the Galaxy S23 Ultra as model `SM-S918B`.  
- [x] **Install the read-only probe app on the modern phone** — P0 — *0.25d* — Completed 2026-05-16 by installing `android/app/build/outputs/apk/debug/app-debug.apk` with `adb install -r`.  
- [x] **Create Kotlin USB probe skeleton** — P0 — *1d* — Confirmed complete 2026-05-16; the Android app already provides a read-only USB enumeration UI in `android/app/src/main/java/com/dexfan/tool/MainActivity.kt`.  
- [x] **Add logging to capture timestamped traces** — P0 — *0.5d* — Confirmed complete 2026-05-16 in `android/app/src/main/java/com/dexfan/tool/ProbeLogger.kt`; timestamped reports are saved to app-internal storage with retention trimming.  
- [x] **Test probe on modern phone (no root)** — P0 — *0.5d* — Completed 2026-05-16; during the docked DeX session the read-only probe app enumerated two USB devices and saved the report to app storage.  
- [x] **If dock visible: expand probe ranges and capture responses** — P0 — *1–2d* — Completed 2026-05-16 with widened read-only vendor IN probes on the Galaxy S23 Ultra: `0x0BDA:0x8152` returned 16-byte all-zero responses for request `0x05` on device and interface recipients; `0x04B4:0x5210` remained non-responsive in the tested range.  
- [ ] **If dock not visible: attempt to free interface (toggle DeX, reboot)** — P1 — *0.5d* — Document steps and results.  
- [ ] **If dock not visible and still needed: validate rooted Note 9 or hardware-analyzer fallback** — P1 — *1d* — Prefer the rooted Note 9 `usbmon` path first, then fall back to a hardware analyzer if the Note 9 cannot be rooted or does not expose `usbmon`.

### Capture & analysis

- [ ] **Validate rooted Note 9 `usbmon` capture path** — P0 — *0.25d* — After backup and root decision, confirm `debugfs` mounts cleanly and `/sys/kernel/debug/usb/usbmon` is available before live capture.  
- [ ] **Capture traffic from rooted Note 9 while DeX / fan changes** — P0 — *1d* — Record `usbmon`, `logcat`, and timing notes while the older phone drives the dock fan.  
- [ ] **Compare rooted Note 9 capture with modern phone behavior** — P0 — *0.5d* — Determine whether the modern path fails at USB visibility or after visibility when vendor commands should occur.  
- [ ] **Analyze captured traces** — P0 — *1–2d* — Identify controlTransfer parameters, request types, values, and timing correlated with fan speeds.  
- [ ] **Document discovered sequences** — P0 — *0.5d* — Add sanitized findings to `docs/` (follow reverse engineering policy).

### Replay & control

- [ ] **Implement safe replay module (reads only first)** — P0 — *1d* — Add UI to send discovered IN/OUT sequences with safety checks.  
- [ ] **Add manual emergency stop and temperature thresholds** — P0 — *0.5d* — App must stop any OUT transfers on threshold.  
- [ ] **Test replay on spare hardware at low duty** — P0 — *0.5d* — Monitor current and temperature.

### Hardware fallback

- [ ] **Open dock and identify fan power pins (non‑destructive inspection)** — P1 — *1d* — Document photos and wiring.  
- [ ] **Design MCU bypass schematic (ESP32/Arduino + MOSFET)** — P1 — *1–2d* — Include safety features and current limiting.  
- [ ] **Prototype MCU controller and test fan speeds** — P1 — *1–2d* — Controlled via USB serial or Bluetooth.

### Documentation & repo hygiene

- [x] **Add TODO.md to repo root** — P0 — *0.1d* — (this file).  
- [x] **Create SETUP.md for dev environment (VS Code + Android SDK)** — P1 — *0.5d* — Created `docs/SETUP.md` on 2026-04-13.  
- [ ] **Add .gitignore and data retention rules** — P1 — *0.25d*.  
- [ ] **Create ISSUE_TEMPLATE and PR_TEMPLATE in .github/** — P2 — *0.5d*.  
- [ ] **Add CHANGELOG and release process** — P2 — *0.5d*.

### Admin & procurement

- [ ] **Procure or borrow USB protocol analyzer (optional)** — P2 — *time dependent* — Add to `hardware/README.md` when available.  
- [ ] **Acquire bench power supply and spare dock/phone** — P1 — *time dependent*.

---

## Future / nice‑to‑have tasks

- [ ] **Automated trace parser (notebook)** — P3 — *2d* — Jupyter notebook to parse and visualize controlTransfer sequences.  
- [ ] **Unit tests for probe logic** — P3 — *1d*.  
- [ ] **Small web dashboard for logs and test results** — P3 — *2–3d*.  
- [ ] **Publish sanitized findings and safe app (after legal review)** — P2 — *TBD*.

---

## Conventions and labels

- **Branch naming:** `feat/<short-desc>`, `fix/<short-desc>`, `docs/<short-desc>`, `exp/<short-desc>` for experiments.  
- **Commit messages:** use Conventional Commits: `feat:`, `fix:`, `docs:`, `chore:`.  
- **Issue labels:** `P0`, `P1`, `research`, `hardware`, `software`, `safety`, `blocked`.

---

## How to claim a task

1. Create a GitHub issue referencing the task line (copy the task text).  
2. Add the appropriate label(s).  
3. Update this `TODO.md` line and change status to `- [~]` (in progress).  
4. When complete, close the issue, change the line to `- [x]`, and add a short note with date and link to the commit/PR.

---

## Tomorrow start here

1. Fill the remaining real hardware details for the successful 2026-05-16 live session in `docs/HARDWARE_INVENTORY.md`: charger, cable, dock, hub, and bench tools.
2. Review whether the successful 2026-05-16 probe log should be retained in `/data` as a sanitized artifact.
3. Probe `0x0BDA:0x8152` `req=0x05` for larger-length boundary behavior while keeping the session non-destructive and tightly logged.
4. If visibility regresses on a repeat test, classify whether the failure looks like fit, power, display, or USB visibility before falling back to the Note 9 path.

## Next immediate actions (recommended)

- [x] **Run the first live dock session** — P0 — *0.5d* — Completed 2026-05-16: the Galaxy S23 Ultra charged, HDMI worked, DeX launched, the app enumerated two USB devices, and the fan remained silent.
- [x] **Install the read-only probe app on the modern phone** — P0 — *0.25d* — Completed 2026-05-16 via `adb install -r`.
- [x] **Verify dock visibility on modern phone (decision gate)** — P0 — *0.5d* — Completed 2026-05-16 with `full DeX plus app visibility`.
- [x] **Confirm dock VID/PID and descriptors** — P0 — *1d* — Completed 2026-05-16 with `0x04B4:0x5210` and `0x0BDA:0x8152` plus interface summaries.
- [x] **Establish baseline One UI / fan-control matrix** — P0 — *0.5d* — Completed 2026-05-16 for Galaxy S23 Ultra / Android 16 / One UI 8.0; fan silent during the read-only session.
- [x] **If dock visible: expand probe ranges and capture responses** — P0 — *1–2d* — Completed 2026-05-16: widened read-only vendor IN probes found non-empty 16-byte responses on `0x0BDA:0x8152` for request `0x05`; `0x04B4:0x5210` remained silent in the same tested range.
- [x] **Characterize `0x0BDA:0x8152` request `0x05` response space** — P0 — *0.5–1d* — Completed 2026-05-16: across recipients `0xC0` and `0xC1`, lengths `8` / `16` / `32`, and tested `value` / `index` pairs `0x0000..0x0003`, all 72 read-only probes returned full-length all-zero payloads.
- [x] **Probe `0x0BDA:0x8152` `req=0x05` for length boundary behavior** — P0 — *0.25–0.5d* — Completed 2026-05-16: the boundary sweep returned full-length all-zero payloads at lengths `8`, `16`, `32`, and `64` for both `0xC0` and `0xC1`, plus a full-length all-zero `128`-byte response for `0xC0`; one likely `0xC1` `len=128` attempt did not respond.
- [x] **Confirm the `0xC1` `len=128` miss and probe beyond `128` bytes** — P0 — *0.25–0.5d* — Completed 2026-05-16: device-recipient `0xC0` reads continued to return full-length all-zero payloads at `128`, `256`, and `512`, while interface-recipient `0xC1` reads responded through `64` bytes and then stopped.
- [x] **Check whether the `0xC1` path has a hard 64-byte limit** — P0 — *0.25–0.5d* — Completed 2026-05-16: a repeat run reproduced the same boundary, with `0xC1` returning full-length all-zero payloads through `64` bytes and no responses at `128`, `256`, or `512`.
- [x] **Probe `0xC0` beyond `512` bytes** — P0 — *0.25–0.5d* — Completed 2026-05-16: device-recipient `0xC0` reads continued to return full-length all-zero payloads at `1024` and `2048` with no short-reads.
- [ ] **Decide whether to keep extending the zero-fill boundary search** — P0 — *0.25d* — Review whether probing beyond `2048` is likely to add value, or whether the modern-phone read-only path has yielded enough evidence to pivot toward capture comparison or a different hypothesis.
- [ ] **Record exact Galaxy Note 9 build and rooted-capture viability** — P0 — *0.25d* — Keep the fallback path ready if modern stock access stops being sufficient.

---
