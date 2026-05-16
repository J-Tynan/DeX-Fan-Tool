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
- A focused characterization run then targeted `0x0BDA:0x8152` `req=0x05` across recipients `0xC0` and `0xC1`, lengths `8`, `16`, and `32`, and small `value` / `index` pairs `0x0000..0x0003`.
- All 72 focused attempts returned full-length payloads, and every returned byte was `00`, indicating a stable but currently content-free read-only response surface in the tested range.
- A follow-up boundary sweep then targeted `0x0BDA:0x8152` `req=0x05` at lengths `8`, `16`, `32`, `64`, and `128` using representative device and interface recipients.
- That boundary sweep returned full-length all-zero payloads through `64` bytes for both `0xC0` and `0xC1`, and a full-length all-zero `128`-byte payload for `0xC0`. One remaining attempt did not respond, most likely the `0xC1` `len=128` case.
- An extended boundary sweep then checked larger lengths on the same path. Device-recipient reads (`0xC0`) continued to return full-length all-zero payloads at `128`, `256`, and `512`, while interface-recipient reads (`0xC1`) stopped responding beyond `64` bytes.
- A repeat run reproduced the same split: `0xC0` again returned full-length all-zero payloads through `512`, while `0xC1` again returned full-length all-zero payloads only through `64` bytes and then stopped.
- A larger follow-up then extended the `0xC0` path to `1024` and `2048` bytes. Both returned full-length all-zero payloads again, with no short-reads.
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

Focused follow-up source app log: `probe-logs/usb-probe-20260516-152517-353.txt`

```text
[2] 0x0BDA:0x8152
Read-only vendor IN probe:
  - Attempted 72 vendor IN probes; non-empty=72, zero-byte=0, no-response=0
  - IN type=0xC0 req=0x05 val=0x0000 idx=0x0000 len=8 -> 8 byte(s): 00 00 00 00 00 00 00 00
  - IN type=0xC1 req=0x05 val=0x0001 idx=0x0001 len=32 -> 32 byte(s): 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
```

Boundary follow-up source app log: `probe-logs/usb-probe-20260516-153344-259.txt`

```text
[2] 0x0BDA:0x8152
Read-only vendor IN probe:
  - Attempted 10 vendor IN probes; non-empty=9, zero-byte=0, no-response=1
  - IN type=0xC0 req=0x05 val=0x0000 idx=0x0000 len=64 -> 64 byte(s): 00 00 00 00 00 00 00 00 ...
  - IN type=0xC0 req=0x05 val=0x0000 idx=0x0000 len=128 -> 128 byte(s): 00 00 00 00 00 00 00 00 ...
  - IN type=0xC1 req=0x05 val=0x0000 idx=0x0000 len=64 -> 64 byte(s): 00 00 00 00 00 00 00 00 ...
```

Extended boundary source app log: `probe-logs/usb-probe-20260516-154030-127.txt`

```text
[2] 0x0BDA:0x8152
Read-only vendor IN probe:
  - Attempted 14 vendor IN probes; non-empty=11, zero-byte=0, no-response=3
  - IN type=0xC0 req=0x05 val=0x0000 idx=0x0000 len=128 -> 128 byte(s): 00 00 00 00 00 00 00 00 ...
  - IN type=0xC0 req=0x05 val=0x0000 idx=0x0000 len=256 -> 256 byte(s): 00 00 00 00 00 00 00 00 ...
  - IN type=0xC0 req=0x05 val=0x0000 idx=0x0000 len=512 -> 512 byte(s): 00 00 00 00 00 00 00 00 ...
  - IN type=0xC1 req=0x05 val=0x0000 idx=0x0000 len=64 -> 64 byte(s): 00 00 00 00 00 00 00 00 ...
```

Repeat-boundary source app log: `probe-logs/usb-probe-20260516-154433-450.txt`

```text
[2] 0x0BDA:0x8152
Read-only vendor IN probe:
  - Attempted 14 vendor IN probes; non-empty=11, zero-byte=0, no-response=3
  - `0xC0` succeeded at lengths 8/16/32/64/128/256/512 with all-zero payloads.
  - `0xC1` succeeded at lengths 8/16/32/64 with all-zero payloads and did not respond at 128/256/512.
```

Large-boundary source app log: `probe-logs/usb-probe-20260516-154827-881.txt`

```text
[2] 0x0BDA:0x8152
Read-only vendor IN probe:
  - Attempted 16 vendor IN probes; non-empty=13, zero-byte=0, no-response=3
  - `0xC0` succeeded at lengths 8/16/32/64/128/256/512/1024/2048 with full-length all-zero payloads.
  - `0xC1` again succeeded at lengths 8/16/32/64 and did not respond at 128/256/512.
```

## Next Read-Only Question

Decide whether extending the device-recipient zero-fill search beyond `2048` is likely to add value, or whether the modern-phone read-only path has already yielded enough evidence to pivot toward comparison capture or a different hypothesis.
