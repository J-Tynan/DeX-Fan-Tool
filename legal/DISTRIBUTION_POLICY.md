# Distribution Policy

## Goals

- Keep the project usable for research, repair, and documentation of original work.
- Avoid distributing materials that may violate vendor terms, copyright, privacy obligations, or anti-circumvention rules.

## Scope

This policy governs publication and redistribution of artifacts that are not plainly covered by the repository's standard source license, including captures, vendor-derived materials, and bundled third-party content.

## Guidelines

- Prefer original code, redacted traces, and high-level behavioral notes.
- Publish only the minimum detail needed to explain findings or reproduce safe experiments.
- Review third-party materials before copying them into `third_party/` or `tools/`.

## Allowed Without Extra Review

- Original repository-authored code and documentation covered by `LICENSE.md`.
- Redacted logs and traces that remove private identifiers and vendor-sensitive payloads.
- High-level notes such as request timing, observed behavior, and safety constraints.

## Requires Owner Review Before Publication

- Detailed vendor-protocol writeups that go beyond high-level behavioral notes.
- Binaries, hardware kits, or packaged releases intended for broader public use.
- Artifacts that include raw captures, reverse-engineered constants, or data copied from vendor systems.
- Any redistribution of third-party tools or materials whose licensing is not already documented.

## Never Publish In This Repository

- Firmware blobs, private keys, secrets, or account-linked identifiers.
- Captures that expose sensitive host data or unreviewed vendor payloads.
- Materials copied from third parties when redistribution rights are unclear.

## Review Trigger

Update this policy before any public binary release, hardware kit release, or publication of vendor-protocol details.

If a planned publication does not clearly fit the categories above, stop and review it before merging or releasing it.
