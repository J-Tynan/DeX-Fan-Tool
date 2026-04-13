# Reverse Engineering Policy

## Purpose

Define rules and boundaries for reverse engineering activities to ensure ethical, legal, and safe conduct.

## Principles

- Prioritize **safety** and **non‑destructive** methods.
- Work within applicable law, contracts, and platform terms.
- Distinguish between original project code and vendor-derived materials when deciding what can be published.
- Avoid publishing vendor proprietary data such as firmware blobs, private keys, or copyrighted firmware images.
- Share **high‑level findings** (e.g., "control transfer sequence A sets fan to low") only if doing so does not violate legal obligations or terms of service.

## Data handling

- Store captured traces and logs in the project repository under `/data` with clear labels and retention notes.
- Do not publish raw firmware or binary blobs without permission.
- Treat traces and logs as sensitive until they have been reviewed and redacted.

## Distribution

- Original repository-authored code may be released under the repository license.
- Vendor-derived materials and sensitive captures must follow `legal/DISTRIBUTION_POLICY.md` before publication.
- Do not distribute tools that enable circumvention of vendor protections without review.

## Review

- Any decision to publish protocol details or tooling must be reviewed and approved by the project owner.
