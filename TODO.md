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
- [ ] **Define license & distribution policy** — P1 — *0.5d* — **Joseph** — `legal/LICENSE.md` and `legal/DISTRIBUTION_POLICY.md`.  
- [ ] **Create CONTRIBUTING.md and CODE_OF_CONDUCT** — P2 — *0.5d* — *unassigned*.

### Research & reconnaissance
- [ ] **Confirm dock VID/PID and descriptors** — P0 — *1d* — **Joseph** — Use phone + USB enumerator to capture device IDs.  
- [ ] **Locate any public references / community work** — P2 — *1d* — *unassigned* — Search forums, GitHub, XDA for EE‑MG950 investigations.  
- [ ] **Inventory available phones and One UI versions** — P1 — *0.5d* — **Joseph** — Record models and OS versions in `docs/HARDWARE_INVENTORY.md`.

### Software probe (non‑destructive)
- [ ] **Create Kotlin USB probe skeleton** — P0 — *1d* — **Joseph** — App enumerates devices, requests permission, performs safe IN `controlTransfer` probes, logs responses.  
- [ ] **Add logging to capture timestamped traces** — P0 — *0.5d* — **Joseph** — Save logs to `/data` with retention rules.  
- [ ] **Test probe on modern phone (no root)** — P0 — *0.5d* — **Joseph** — Verify whether dock appears as `UsbDevice`.  
- [ ] **If dock visible: expand probe ranges and capture responses** — P0 — *1–2d* — **Joseph** — Carefully iterate vendor IN requests; log all non-empty responses.  
- [ ] **If dock not visible: attempt to free interface (toggle DeX, reboot)** — P1 — *0.5d* — **Joseph** — Document steps and results.  
- [ ] **If dock not visible and still needed: plan capture with rooted phone or hardware sniffer** — P1 — *1d* — **Joseph** — Decide path and procure tools.

### Capture & analysis
- [ ] **Capture traffic from older One UI phone (if available)** — P0 — *1d* — **Joseph** — Use usbmon or hardware sniffer to record host→dock sequences while fan changes.  
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
- [ ] **Create SETUP.md for dev environment (VS Code + Android SDK)** — P1 — *0.5d* — **Joseph**.  
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

## Next immediate actions (recommended)
- [~] **Create GitHub issues** for the top P0 tasks: *Confirm dock VID/PID*, *Create Kotlin USB probe skeleton*, *Capture from older One UI if available*.  
- [ ] **Add SETUP.md** so contributors can build and run the probe app quickly.

---
