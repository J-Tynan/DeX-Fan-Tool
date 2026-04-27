# Contributing

## Start Here

Before opening a pull request, read these project documents:

- `docs/PROJECT_CHARTER.md`
- `docs/RISK_AND_SAFETY.md`
- `docs/REVERSE_ENGINEERING_POLICY.md`
- `legal/DISTRIBUTION_POLICY.md`
- `.github/CODE_OF_CONDUCT.md`

This repository is safety-sensitive. Read-only probing is the default, and anything that can change fan behavior or power delivery must be documented conservatively.

## Workflow

1. Open or reference an issue that describes the change, experiment, or documentation update.
2. Keep each change focused on one area when possible.
3. Document hardware assumptions, protocol assumptions, and safety constraints in the relevant docs file.
4. Include validation notes and any safety implications in the pull request.
5. Update `TODO.md` when a tracked task changes state.

## Safety And Experiment Rules

- Prefer non-destructive tests first.
- Use a powered hub, current-limited supply, and a clear abort path for live hardware tests.
- Any invasive, destructive, or higher-risk test requires documented sign-off before execution.
- Stop testing immediately if you observe abnormal heat, smell, sound, or current draw.
- If a change can affect fan control or power delivery, describe rollback or emergency-stop steps in the PR.

## Data And Artifact Handling

- Treat USB traces, logs, and captures as sensitive until reviewed and redacted.
- Do not commit firmware blobs, private keys, secrets, or raw sensitive captures.
- Store live logs locally first and promote only reviewed excerpts into tracked repository content.
- Add provenance notes for anything placed in `third_party/` or `tools/`.

## Code And Documentation Expectations

- Keep scripts small, inspectable, and easy to review.
- Prefer targeted changes over broad refactors.
- Use the repository's Conventional Commit prefixes: `feat:`, `fix:`, `docs:`, `chore:`.
- Use the branch naming guidance from `TODO.md` when you create topic branches.
- Update docs when behavior, setup, or safety expectations change.

## Pull Requests

- Summarize what changed and how it was validated.
- Call out any safety implications, data sensitivity concerns, or release-review needs.
- Link any related issue, task, or experiment log.
- Remove sensitive data before requesting review.

## Licensing And Publication Review

- Original repository-authored code and documentation follow the terms in `LICENSE.md` unless a file says otherwise.
- Publication of traces, vendor-derived materials, binaries, and packaged hardware guidance must follow `legal/DISTRIBUTION_POLICY.md`.
- When in doubt, stop and ask for review before publishing or merging sensitive material.
