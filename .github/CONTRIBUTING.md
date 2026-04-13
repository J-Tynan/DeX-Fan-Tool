# Contributing

## Before You Start
- Read `docs/PROJECT_CHARTER.md`, `docs/RISK_AND_SAFETY.md`, and `docs/REVERSE_ENGINEERING_POLICY.md`.
- Prefer non-destructive tests first.
- Do not commit raw sensitive traces, secrets, or private keys.

## Workflow
1. Open an issue describing the problem or experiment.
2. Keep changes scoped to one area when possible.
3. Document any hardware or protocol assumptions in the relevant README or docs file.
4. Include validation notes in the pull request.

## Code And Data Standards
- Keep scripts small and inspectable.
- Treat USB traces and logs as sensitive until reviewed.
- Add provenance notes for anything placed in `third_party/`.

## Safety
- Read-only probing is the default.
- Any change that can affect fan behavior or power delivery should mention rollback or emergency-stop steps.
