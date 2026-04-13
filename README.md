# [WIP] DeX Fan Tool

## Short description

Restore fan control for the Samsung DeX Station EE‑MG950 by reverse engineering the USB control sequence or providing a documented hardware bypass.

## Status

Planning and safe probing phase.

## Quick start

1. Read `docs/PROJECT_CHARTER.md` and `docs/RISK_AND_SAFETY.md`.
2. Set up development environment (Android SDK, adb). See `docs/SETUP.md`.
3. Run the USB probe app in `android/` to enumerate devices and capture safe IN responses.
4. Follow the Test Plan in `docs/TEST_PLAN.md`.

## Safety

- Start with read-only probes.
- Use a powered hub and bench supply with current limiting.
- Use spare hardware for destructive tests.

## Repository layout

- `/docs` — project documents and policies
- `/android` — Kotlin app for USB probing
- `/firmware` — hardware bypass code (if needed)
- `/data` — captured traces and logs (sensitive; do not publish raw firmware)

## License

See `LICENSE.md`.

## Contact

Project owner: Joseph
