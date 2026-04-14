# [WIP] DeX Fan Tool

## Short description

Restore fan control for the Samsung DeX Station EE‑MG950 by reverse engineering the USB control sequence or providing a documented hardware bypass.

![Samsung DeX Station EE‑MG950](hardware/photos/DeX%20Station/EE-MG950_06_R-Slide-Open%20(Small%20540p).jpeg)

## Status

Build-ready, read-only probing phase.

- Android wrapper is checked in and the debug build has been validated.
- The current Android app enumerates visible USB devices without sending control transfers.
- The Android app now saves timestamped enumeration reports in app-internal storage with simple retention.
- The next gate is confirming `adb` device visibility and running the probe on a physical phone.

## Quick start

1. Read `docs/PROJECT_CHARTER.md` and `docs/RISK_AND_SAFETY.md`.
2. Set up development environment (Android SDK, adb). See `docs/SETUP.md`.
3. Build the Android probe from `android/` with the checked-in Gradle wrapper.
4. Connect a test phone, verify `adb devices`, then install and run the read-only probe app.
5. Follow the Test Plan in `docs/TEST_PLAN.md`.

## Safety

- Start with read-only probes.
- Use a powered hub and bench supply with current limiting.
- Use spare hardware for destructive tests.

## Governance

- Contribution guide: `.github/CONTRIBUTING.md`
- Code of conduct: `.github/CODE_OF_CONDUCT.md`
- Reverse engineering policy: `docs/REVERSE_ENGINEERING_POLICY.md`
- Distribution policy: `legal/DISTRIBUTION_POLICY.md`

## Repository layout

- `/docs` — project documents and policies
- `/android` — Kotlin app for USB probing
- `/firmware` — hardware bypass code (if needed)
- `/data` — reviewed traces and logs retained in the repository (sensitive; do not publish raw firmware)

## License

See `LICENSE.md`. Distribution of sensitive captures, vendor-derived materials, and bundled third-party artifacts is further constrained by `legal/DISTRIBUTION_POLICY.md`.

## Contact

Project owner: Joseph
