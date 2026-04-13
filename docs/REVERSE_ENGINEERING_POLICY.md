# Reverse Engineering Policy

## Purpose
Define rules and boundaries for reverse engineering activities to ensure ethical, legal, and safe conduct.

## Principles
- Work is for **personal, non‑commercial use** unless explicit legal review and permission are obtained.
- Prioritize **safety** and **non‑destructive** methods.
- Avoid publishing vendor proprietary data such as firmware blobs, private keys, or copyrighted firmware images.
- Share **high‑level findings** (e.g., "control transfer sequence A sets fan to low") only if doing so does not violate legal obligations or terms of service.

## Data handling
- Store captured traces and logs in the project repository under `/data` with clear labels and retention notes.
- Do not publish raw firmware or binary blobs without permission.

## Distribution
- If publishing code, prefer **open source license** that disclaims liability and includes safety warnings.
- Do not distribute tools that enable circumvention of vendor protections without legal review.

## Review
- Any decision to publish protocol details or tooling must be reviewed and approved by the project owner.