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

## Governance

- Contribution guide: `.github/CONTRIBUTING.md`
- Code of conduct: `.github/CODE_OF_CONDUCT.md`
- Reverse engineering policy: `docs/REVERSE_ENGINEERING_POLICY.md`
- Distribution policy: `legal/DISTRIBUTION_POLICY.md`

## Repository layout

- `/docs` — project documents and policies
- `/android` — Kotlin app for USB probing
- `/firmware` — hardware bypass code (if needed)
- `/data` — captured traces and logs (sensitive; do not publish raw firmware)

## License

See `LICENSE.md`. Distribution of sensitive captures, vendor-derived materials, and bundled third-party artifacts is further constrained by `legal/DISTRIBUTION_POLICY.md`.

## Contact

Project owner: Joseph
