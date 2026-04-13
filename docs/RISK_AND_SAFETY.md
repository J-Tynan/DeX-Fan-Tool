# Risk Assessment and Safety Plan

## Summary
This document lists identified risks, mitigations, and emergency procedures for safe experimentation with the EE‑MG950 dock.

## Risks
- **Electrical damage** to dock, phone, or fan from incorrect commands or wiring.
- **Overheating** if fan control fails during stress tests.
- **Voiding warranty** by opening or modifying hardware.
- **Legal risk** if reverse‑engineered protocol is published in violation of terms.
- **Data loss** on test phones if system services are disrupted.

## Mitigations
- Use a **powered USB hub** and **bench power supply** with current limiting for all tests.
- Start with **IN (read) control transfers only**; avoid OUT transfers until protocol is understood.
- Use **spare phones** and spare dock units for destructive tests.
- Add **software safety limits**: maximum duty, manual emergency stop, and temperature thresholds.
- Keep a **multimeter** and **thermal sensor** available during tests.
- Document all steps and keep a **change log**.

## Emergency stop procedures
- **Software**: immediate stop button in the app that halts any OUT transfers and returns to safe state.
- **Hardware**: bench supply power switch; unplug USB if smoke or abnormal heat is detected.
- **Post-incident**: record incident in CHANGELOG.md and RISK_AND_SAFETY.md, preserve hardware for analysis.

## Approval
All destructive or invasive tests require explicit sign-off from the project owner.