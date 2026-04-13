# Manual Test Cases

## USB Enumeration
1. Connect the dock through a powered hub.
2. Launch the Android app.
3. Confirm device identifiers are displayed or logged.

## Safe Probe Session
1. Run only read-only probes.
2. Monitor thermals and current draw.
3. Save redacted logs to `data/`.

## Replay Dry Run
1. Prepare a reviewed JSON sequence.
2. Run `scripts/replay_sequence.py <sequence.json>`.
3. Verify the printed summary matches expectations before any live replay code is written.
