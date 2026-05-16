# 2026-05-16 S23 Ultra Read-Only IN Probe Summary

This is a reviewed and sanitized summary of the successful read-only probe milestone captured on 2026-05-16.

## Environment

- Phone: Samsung Galaxy S23 Ultra (`SM-S918B`)
- Software: Android 16 / One UI 8.0
- Dock: Samsung DeX Station `EE-MG950`
- Session mode: Samsung DeX launched successfully; charging and HDMI were both working
- Safety mode: read-only USB enumeration plus vendor IN probing only; no USB OUT control transfers were sent

## Visible USB Functions

- `0x04B4:0x5210`
- `0x0BDA:0x8152`

## Reviewed Findings

- The first conservative vendor IN sweep (`req=0x00..0x03`) returned no data on either visible device.
- The widened read-only sweep (`req=0x00..0x07`, device and interface recipients) produced the first non-empty responses on `0x0BDA:0x8152`.
- `0x04B4:0x5210` remained non-responsive in the tested request window.
- The non-empty responses seen so far are all-zero 16-byte payloads, which suggests the endpoint is reachable but does not yet prove fan-control semantics.
- The dock fan remained silent throughout these read-only sessions.

## Reviewed Response Excerpts

Source app log: `probe-logs/usb-probe-20260516-151747-805.txt`

```text
[2] 0x0BDA:0x8152
Read-only vendor IN probe:
  - Attempted 40 vendor IN probes; non-empty=5, zero-byte=0, no-response=35
  - IN type=0xC0 req=0x05 val=0x0000 idx=0x0000 -> 16 byte(s): 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
  - IN type=0xC1 req=0x05 val=0x0000 idx=0x0000 -> 16 byte(s): 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
  - IN type=0xC1 req=0x05 val=0x0000 idx=0x0001 -> 16 byte(s): 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
```

## Next Read-Only Question

Map the `0x0BDA:0x8152` request `0x05` response space by varying requested length and small `value` / `index` pairs while keeping the session tightly logged and fully read-only.