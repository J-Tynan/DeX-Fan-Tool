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

## Non-root thermal monitoring assumptions

- A non-root Android app can usually access **battery temperature**, **charging state**, and coarse **system thermal status** through public Android APIs.
- A non-root Android app should not assume access to **kernel thermal zones**, **per-sensor board temperatures**, or other privileged Samsung thermal internals.
- Root may still be required for **protocol discovery** on an older device, but that does not by itself rule out a later non-root fan-control app.
- The real deployment constraint is whether a stock phone can both **see the dock through normal Android USB APIs** and **send the required control transfers**.

## Draft thermal policy direction

- Prefer a **layered thermal policy** rather than a single threshold.
- Use **session gating**: enable automatic fan policy only when the phone is in a dock-relevant state such as external display / DeX activity, charging through the dock, or confirmed dock visibility.
- Use a **conservative baseline fan level** during active dock sessions so cooling starts before the phone is already heat-soaked.
- Escalate fan output using public signals such as **battery temperature**, **overall thermal severity**, and observed **temperature rise rate** when available.
- Add **hysteresis and hold times** so fan speed does not oscillate rapidly when temperatures hover near a threshold.
- Keep a **manual emergency stop** and a **high-temperature override** that forces the safest known fan state and warns the user if thermal conditions continue to worsen.

## Design implication

- The project can likely recreate much of the dock's original utility with a non-root app if compatible phones expose enough USB access and public thermal signals.
- The likely support target is a **compatible subset of Samsung / DeX-capable devices**, not every Android phone.
- If public USB access proves insufficient on stock devices, the fallback remains **rooted-only tooling** or the documented **hardware bypass** path.

## Emergency stop procedures

- **Software**: immediate stop button in the app that halts any OUT transfers and returns to safe state.
- **Hardware**: bench supply power switch; unplug USB if smoke or abnormal heat is detected.
- **Post-incident**: record incident in CHANGELOG.md and RISK_AND_SAFETY.md, preserve hardware for analysis.

## Approval

All destructive or invasive tests require explicit sign-off from the project owner.
