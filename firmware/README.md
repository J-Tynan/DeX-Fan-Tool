# Firmware Workspace

This folder is reserved for microcontroller-based bypass experiments if USB-only fan control cannot be restored safely.

## Structure
- `esp32/` contains starter notes for an ESP32-based controller.

## Rules
- Document pin mappings before flashing hardware.
- Keep default firmware fail-safe: fan on or known-safe state on boot.
- Record measured voltages, current draw, and thermal observations in `docs/` or `data/`.
