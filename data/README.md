# Data Handling Rules

Use this folder for redacted USB traces, measurement CSV files, and experiment artifacts that are safe to retain.

## Retention And Sensitivity
- Do not commit raw captures that include private identifiers, firmware blobs, or sensitive host data.
- Prefer CSV summaries and redacted excerpts over full packet dumps.
- `*.pcap` files are ignored by default; keep only reviewed sample traces if explicitly unignored.
