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

## Requires Project Review Before Publication

- Detailed vendor-protocol writeups that go beyond high-level behavioral notes.
- Binaries, hardware kits, or packaged releases intended for broader public use.
- Artifacts that include raw captures, reverse-engineered constants, or data copied from vendor systems.
- Any redistribution of third-party tools or materials whose licensing is not already documented.

## Project Review Workflow

When an artifact falls into `Requires Project Review Before Publication`, use this workflow before merging or releasing it.

1. Create a tracking record in a GitHub issue, pull request, or release-preparation note that identifies the artifact and intended publication target.
2. Classify the artifact as one of: `original code`, `redacted trace`, `protocol summary`, `binary release`, `third-party material`, or `restricted / do not publish`.
3. Record the basis for publication: what was redacted, what source it came from, and why it is believed safe to publish.
4. If third-party content is involved, record the license or redistribution basis before publication.
5. Obtain explicit project approval in the same issue, pull request, or release-preparation note before merging or releasing the artifact.
6. Keep the approval record linked from the related pull request, release notes, or changelog entry when a public release is made.

## Pre-Publication Checklist

Use this checklist for any artifact that is not plainly covered by `LICENSE.md`.

- Confirm the artifact category and intended audience are documented.
- Confirm raw captures, secrets, account-linked identifiers, and unreviewed vendor payloads are excluded.
- Confirm traces are redacted to the minimum detail needed for safe reproduction or explanation.
- Confirm any third-party tool, file, or reference material has a documented license or redistribution basis.
- Confirm the artifact belongs in the intended location: `docs/`, `data/`, `third_party/`, release assets, or nowhere public.
- Confirm project approval is recorded if the artifact falls under `Requires Project Review Before Publication`.
- Confirm the related issue, pull request, or release notes link to the approval and classification record.

## Never Publish In This Repository

- Firmware blobs, private keys, secrets, or account-linked identifiers.
- Captures that expose sensitive host data or unreviewed vendor payloads.
- Materials copied from third parties when redistribution rights are unclear.

## Review Trigger

Update this policy before any public binary release, hardware kit release, or publication of vendor-protocol details.

If a planned publication does not clearly fit the categories above, stop and review it before merging or releasing it.
