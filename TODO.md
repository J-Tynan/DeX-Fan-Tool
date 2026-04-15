# DeX Fan Restore — TODO

**Purpose:** single source of truth for project tasks, priorities, owners, and progress tracking.

---

## How to use this file

- **Status markers:** `- [ ]` = open, `- [~]` = in progress, `- [x]` = done.  
- **Priority:** `P0` (critical), `P1` (high), `P2` (medium), `P3` (low).  
- **Estimate:** rough timebox in hours or days.  
- **Owner:** person responsible for completing the task. If unassigned, leave blank.  
- **Dependencies:** list tasks that must finish first.  
- **Updating:** when you start a task change `- [ ]` → `- [~]`; when complete change to `- [x]` and add a short note with date and link to any related commit or issue.

---

## Current starter tasks

### Planning & governance

- [x] **Project charter** — P0 — *0.5d* — **Joseph** — Created `docs/PROJECT_CHARTER.md`.  
- [x] **Scope & non-goals** — P0 — *0.25d* — **Joseph** — Created `docs/SCOPE.md`.  
- [x] **Risk & safety plan** — P0 — *0.5d* — **Joseph** — Created `docs/RISK_AND_SAFETY.md`.  
- [x] **Repository skeleton** — P1 — *0.5d* — **Joseph** — Repo created and initial docs uploaded.  
- [~] **Define license & distribution policy** — P1 — *0.5d* — **Joseph** — Implementation started 2026-04-13 in `LICENSE.md`, `legal/LICENSE.md`, and `legal/DISTRIBUTION_POLICY.md`.  
- [x] **Create CONTRIBUTING.md and CODE_OF_CONDUCT** — P2 — *0.5d* — **Joseph** — Implementation started 2026-04-13 in `.github/CONTRIBUTING.md` and `.github/CODE_OF_CONDUCT.md`.

### Research & reconnaissance

- [~] **Inventory available phones and One UI versions** — P0 — *0.5d* — **Joseph** — Populate exact device model, Android / One UI version, USB-C data status, and selected first-test hardware in `docs/HARDWARE_INVENTORY.md`.  
- [ ] **Record exact Galaxy Note 9 build and rooted-capture viability** — P0 — *0.25d* — **Joseph** — Confirm exact model / region, Android and One UI version, bootloader unlock status, backup state, and whether the phone still triggers DeX Station fan behavior before attempting root.  
- [x] **Verify test bench readiness before first live USB test** — P0 — *0.5d* — **Joseph** — Confirm powered hub, smartphones, cables, power path, and abort path before live enumeration.  
- [ ] **Verify dock visibility on modern phone (decision gate)** — P0 — *0.5d* — **Joseph** — Use the existing Kotlin probe on the primary modern phone; if visible continue to VID/PID capture, otherwise pivot to fallback tasks.  
- [ ] **Confirm dock VID/PID and descriptors** — P0 — *1d* — **Joseph** — If the dock is visible to Android, capture vendor/product IDs and descriptors with the USB enumerator.  
- [ ] **Establish baseline One UI / fan-control matrix** — P0 — *0.5d* — **Joseph** — Record which phone / OS combinations are confirmed to drive the fan, fail, or remain unverified.  
- [x] **Locate any public references / community work** — P2 — *1d* — **Joseph** — Initial survey documented 2026-04-14 in `docs/COMMUNITY_REFERENCES.md`; findings include mixed S23/S24 compatibility anecdotes, charger/cable sensitivity, and no clear public fan-control reverse-engineering repo.

### Software probe (non‑destructive)

- [x] **Install and configure JDK 17 for Android builds** — P0 — *0.25d* — **Joseph** — Installed Eclipse Temurin 17 and set `JAVA_HOME` on 2026-04-13; project config targets Java 17.  
- [x] **Install and configure Android SDK and platform-tools** — P0 — *0.25d* — **Joseph** — Configured `ANDROID_SDK_ROOT` / `ANDROID_HOME`, provisioned `sdkmanager`, and verified Platform 34, Build-Tools 34.0.0, and `adb`; re-verified locally on 2026-04-14.  
- [x] **Install local Gradle and generate the Android wrapper** — P0 — *0.25d* — **Joseph** — Installed local Gradle 8.7 and generated `gradlew.bat` plus `gradle-wrapper.jar` on 2026-04-13.  
- [x] **Validate Android project sync and debug build** — P0 — *0.25d* — **Joseph** — Verified wrapper task listing and successful `assembleDebug` build on 2026-04-13 after enabling AndroidX.  
- [ ] **Verify modern phone is visible to adb** — P0 — *0.25d* — **Joseph** — Enable developer options / USB debugging and confirm the primary phone appears in `adb devices`.  
- [ ] **Install the read-only probe app on the modern phone** — P0 — *0.25d* — **Joseph** — Deploy the current app build to the primary test phone before running the USB visibility gate.  
- [ ] **Create Kotlin USB probe skeleton** — P0 — *1d* — **Joseph** — Read-only USB enumeration UI added; next step is safe probe scaffolding after live visibility test.  
- [ ] **Add logging to capture timestamped traces** — P0 — *0.5d* — **Joseph** — Save logs to `/data` with retention rules.  
- [ ] **Test probe on modern phone (no root)** — P0 — *0.5d* — **Joseph** — Execute the Research & reconnaissance visibility gate and record whether the dock appears as `UsbDevice`.  
- [ ] **If dock visible: expand probe ranges and capture responses** — P0 — *1–2d* — **Joseph** — Carefully iterate vendor IN requests; log all non-empty responses.  
- [ ] **If dock not visible: attempt to free interface (toggle DeX, reboot)** — P1 — *0.5d* — **Joseph** — Document steps and results.  
- [ ] **If dock not visible and still needed: validate rooted Note 9 or hardware-analyzer fallback** — P1 — *1d* — **Joseph** — Prefer the rooted Note 9 `usbmon` path first, then fall back to a hardware analyzer if the Note 9 cannot be rooted or does not expose `usbmon`.

### Capture & analysis

- [ ] **Validate rooted Note 9 `usbmon` capture path** — P0 — *0.25d* — **Joseph** — After backup and root decision, confirm `debugfs` mounts cleanly and `/sys/kernel/debug/usb/usbmon` is available before live capture.  
- [ ] **Capture traffic from rooted Note 9 while DeX / fan changes** — P0 — *1d* — **Joseph** — Record `usbmon`, `logcat`, and timing notes while the older phone drives the dock fan.  
- [ ] **Compare rooted Note 9 capture with modern phone behavior** — P0 — *0.5d* — **Joseph** — Determine whether the modern path fails at USB visibility or after visibility when vendor commands should occur.  
- [ ] **Analyze captured traces** — P0 — *1–2d* — **Joseph** — Identify controlTransfer parameters, request types, values, and timing correlated with fan speeds.  
- [ ] **Document discovered sequences** — P0 — *0.5d* — **Joseph** — Add sanitized findings to `docs/` (follow reverse engineering policy).

### Replay & control

- [ ] **Implement safe replay module (reads only first)** — P0 — *1d* — **Joseph** — Add UI to send discovered IN/OUT sequences with safety checks.  
- [ ] **Add manual emergency stop and temperature thresholds** — P0 — *0.5d* — **Joseph** — App must stop any OUT transfers on threshold.  
- [ ] **Test replay on spare hardware at low duty** — P0 — *0.5d* — **Joseph** — Monitor current and temperature.

### Hardware fallback

- [ ] **Open dock and identify fan power pins (non‑destructive inspection)** — P1 — *1d* — **Joseph** — Document photos and wiring.  
- [ ] **Design MCU bypass schematic (ESP32/Arduino + MOSFET)** — P1 — *1–2d* — *unassigned* — Include safety features and current limiting.  
- [ ] **Prototype MCU controller and test fan speeds** — P1 — *1–2d* — *unassigned* — Controlled via USB serial or Bluetooth.

### Documentation & repo hygiene

- [x] **Add TODO.md to repo root** — P0 — *0.1d* — **Joseph** — (this file).  
- [x] **Create SETUP.md for dev environment (VS Code + Android SDK)** — P1 — *0.5d* — **Joseph** — Created `docs/SETUP.md` on 2026-04-13.  
- [ ] **Add .gitignore and data retention rules** — P1 — *0.25d* — **Joseph**.  
- [ ] **Create ISSUE_TEMPLATE and PR_TEMPLATE in .github/** — P2 — *0.5d* — *unassigned*.  
- [ ] **Add CHANGELOG and release process** — P2 — *0.5d* — *unassigned*.

### Admin & procurement

- [ ] **Procure or borrow USB protocol analyzer (optional)** — P2 — *time dependent* — **Joseph** — Add to `hardware/README.md` when available.  
- [ ] **Acquire bench power supply and spare dock/phone** — P1 — *time dependent* — **Joseph**.

---

## Future / nice‑to‑have tasks

- [ ] **Automated trace parser (notebook)** — P3 — *2d* — *unassigned* — Jupyter notebook to parse and visualize controlTransfer sequences.  
- [ ] **Unit tests for probe logic** — P3 — *1d* — *unassigned*.  
- [ ] **Small web dashboard for logs and test results** — P3 — *2–3d* — *unassigned*.  
- [ ] **Publish sanitized findings and safe app (after legal review)** — P2 — *TBD* — **Joseph**.

---

## Conventions and labels

- **Branch naming:** `feat/<short-desc>`, `fix/<short-desc>`, `docs/<short-desc>`, `exp/<short-desc>` for experiments.  
- **Commit messages:** use Conventional Commits: `feat:`, `fix:`, `docs:`, `chore:`.  
- **Issue labels:** `P0`, `P1`, `research`, `hardware`, `software`, `safety`, `blocked`.

---

## How to claim a task

1. Create a GitHub issue referencing the task line (copy the task text).  
2. Assign the issue to yourself and add the appropriate label(s).  
3. Update this `TODO.md` line to set the owner and change status to `- [~]` (in progress).  
4. When complete, close the issue, change the line to `- [x]`, and add a short note with date and link to the commit/PR.

---

## Tomorrow start here

1. Fill the real hardware details for the first live session in `docs/HARDWARE_INVENTORY.md`: actual phone model / OS, charger, cable, dock, hub, and bench tools.
2. Walk through `docs/FIRST_LIVE_DOCK_CHECKLIST.md` before connecting the phone, especially the power, cabling, and physical-fit checks.
3. Complete **Verify modern phone is visible to adb**.
4. Complete **Install the read-only probe app on the modern phone**.
5. Complete **Verify dock visibility on modern phone (decision gate)** and record the result as one of: `no charge`, `charge but no HDMI`, `DeX works but app cannot see dock`, or `full DeX plus app visibility`.
6. If the dock is visible to Android, complete **Confirm dock VID/PID and descriptors** immediately and then update **Establish baseline One UI / fan-control matrix**.
7. If the dock is not visible, retry once with the case removed and once with a second known-good fast/adaptive charger and cable, then document whether the failure looks like fit, power, display, or USB visibility.
8. If the modern phone still cannot see the dock, switch to the Note 9 fallback path: record the exact build, confirm the dock still drives the fan, and validate whether root plus `usbmon` is realistic before buying more hardware.

## Next immediate actions (recommended)

- [ ] **Run the first live dock session** — P0 — *0.5d* — **Joseph** — Use `docs/FIRST_LIVE_DOCK_CHECKLIST.md` and update `docs/HARDWARE_INVENTORY.md` with real outcomes.
- [ ] **Verify modern phone is visible to adb** — P0 — *0.25d* — **Joseph** — Enable developer options / USB debugging and confirm the primary phone appears in `adb devices`.
- [ ] **Install the read-only probe app on the modern phone** — P0 — *0.25d* — **Joseph** — Deploy the current app build to the primary test phone before running the USB visibility gate.
- [ ] **Verify dock visibility on modern phone (decision gate)** — P0 — *0.5d* — **Joseph** — Record whether the dock is visible, and classify any failure mode precisely.
- [ ] **Confirm dock VID/PID and descriptors** — P0 — *1d* — **Joseph** — If the dock is visible to Android, capture vendor/product IDs and descriptors with the USB enumerator.
- [ ] **Establish baseline One UI / fan-control matrix** — P0 — *0.5d* — **Joseph** — Promote community assumptions to local outcomes after the first live session.
- [ ] **If modern visibility still fails: validate the Note 9 `usbmon` fallback** — P1 — *0.5d* — **Joseph** — Confirm exact Note 9 build, whether the dock still drives the fan, and whether root plus `usbmon` is available before moving to a hardware analyzer.

---
