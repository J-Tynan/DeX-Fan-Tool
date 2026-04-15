# [WIP] DeX Fan Tool

Restore fan control for the Samsung DeX Station EE‑MG950 by reverse engineering the USB control sequence or providing a documented hardware bypass.

![Samsung DeX Station EE‑MG950](hardware/photos/DeX%20Station/EE-MG950_06_R-Slide-Open%20(Small%20540p).jpeg)

## Status

Build-ready, read-only probing phase with a documented fallback capture path.

- Android wrapper is checked in and the debug build has been validated.
- The current Android app enumerates visible USB devices without sending control transfers.
- The Android app now saves timestamped enumeration reports in app-internal storage with simple retention.
- The preferred path is still a non-root solution on compatible phones using normal Android USB access.
- The next gate is confirming `adb` device visibility and running the probe on a physical modern phone.
- If the modern-phone visibility gate fails, the documented fallback is a rooted Galaxy Note 9 capture path using `usbmon` before committing to more invasive or expensive alternatives.
- A formal go/no-go guide for irreversible steps is now documented in [docs/ROOTING_DECISION_MATRIX.md](docs/ROOTING_DECISION_MATRIX.md).

## Quick start

1. Read [docs/PROJECT_CHARTER.md](docs/PROJECT_CHARTER.md), [docs/RISK_AND_SAFETY.md](docs/RISK_AND_SAFETY.md), and [docs/ROOTING_DECISION_MATRIX.md](docs/ROOTING_DECISION_MATRIX.md).
2. Set up development environment (Android SDK, adb). See [docs/SETUP.md](docs/SETUP.md).
3. Fill the live hardware details in [docs/HARDWARE_INVENTORY.md](docs/HARDWARE_INVENTORY.md) and review [docs/FIRST_LIVE_DOCK_CHECKLIST.md](docs/FIRST_LIVE_DOCK_CHECKLIST.md).
4. Build the Android probe from `android/` with the checked-in Gradle wrapper.
5. Connect a test phone, verify `adb devices`, then install and run the read-only probe app.
6. Follow [docs/TEST_PLAN.md](docs/TEST_PLAN.md) for either the modern-phone visibility gate or the rooted Note 9 fallback capture path.

## Safety

- Start with read-only probes.
- Use a powered hub and bench supply with current limiting.
- Use spare hardware for destructive tests.

## Governance

- Contribution guide: [.github/CONTRIBUTING.md](.github/CONTRIBUTING.md)
- Code of conduct: [.github/CODE_OF_CONDUCT.md](.github/CODE_OF_CONDUCT.md)
- Reverse engineering policy: [docs/REVERSE_ENGINEERING_POLICY.md](docs/REVERSE_ENGINEERING_POLICY.md)
- Distribution policy: [legal/DISTRIBUTION_POLICY.md](legal/DISTRIBUTION_POLICY.md)

## Repository layout

- `/docs` — project documents and policies
- `/android` — Kotlin app for USB probing
- `/hardware` — dock inventory, photos, and hardware fallback notes
- `/firmware` — hardware bypass code (if needed)
- `/data` — reviewed traces and logs retained in the repository (sensitive; do not publish raw firmware)

## License

See [LICENSE.md](LICENSE.md). Distribution of sensitive captures, vendor-derived materials, and bundled third-party artifacts is further constrained by [legal/DISTRIBUTION_POLICY.md](legal/DISTRIBUTION_POLICY.md).

## Contact

Project owner: Joseph
