# First Live Dock Checklist

Use this checklist before the first live `EE-MG950` test with a modern phone. The goal is to rule out simple fit, charger, and power-path problems before assuming an Android USB visibility failure.

## Pre-Flight

- [ ] Confirm the dock under test is identified in `docs/HARDWARE_INVENTORY.md`.
- [ ] Confirm the phone under test is identified in `docs/HARDWARE_INVENTORY.md`.
- [ ] Confirm the current test remains read-only from the app side.
- [ ] Confirm the bench abort path is ready: power switch, cable pull, and app exit path.
- [ ] Confirm a multimeter is on hand.

## Power And Cabling

- [ ] Use a known-good fast/adaptive charger, not an unknown USB-C power source.
- [ ] Use a known-good USB-C power cable with no visible damage.
- [ ] Record the charger make/model and wattage in `docs/HARDWARE_INVENTORY.md`.
- [ ] Connect HDMI only after power is stable, then note whether the phone charges before checking display output.

## Physical Fit

- [ ] Inspect the dock USB-C plug and phone USB-C port for debris or damage.
- [ ] Test with the phone case removed if there is any doubt about connector depth.
- [ ] If the case stays on, confirm the bottom edge is fully open around the USB-C connector.
- [ ] Note whether the phone feels fully seated and mechanically stable in the dock.

## Outcome Recording

Record each outcome separately. Do not collapse them into a single "works" or "doesn't work" note.

- [ ] Phone charges in dock: Yes / No
- [ ] HDMI output appears: Yes / No
- [ ] DeX launches: Yes / No
- [ ] Android app sees dock in `UsbManager.getDeviceList()`: Yes / No
- [ ] Dock fan behavior observed: silent / spins / unknown
- [ ] Any abnormal heat, smell, or noise: Yes / No

## If The Dock Does Not Behave As Expected

- [ ] Retry once with the case removed.
- [ ] Retry once with a second known-good fast/adaptive charger.
- [ ] Retry once with a second known-good cable.
- [ ] If there is charging but no display, record it as a display-path issue rather than a total dock failure.
- [ ] If there is no charging at all, record it as a power-path issue first.
- [ ] If DeX works but the Android app does not see the dock, record that as a USB visibility failure rather than a general compatibility failure.

## Post-Test Notes

- [ ] Save Android probe logs.
- [ ] Copy reviewed notes or sanitized artifacts into `/data` only after review.
- [ ] Update `docs/HARDWARE_INVENTORY.md` with the real outcome.
- [ ] Update the One UI / compatibility matrix task notes in `TODO.md` if the result changes project direction.
