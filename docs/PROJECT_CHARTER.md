# Project Charter — DeX Station Fan Restore

**Project name**: DeX Fan Restore (EE-MG950)

**Purpose**: Restore fan control for the Samsung DeX Station EE‑MG950 so the dock’s fan can be driven safely from modern phones.

**Scope**  
**In scope**: software probing from Android, safe replay of commands, a small Android app to control fan speeds, documentation, and a documented hardware bypass option if required.  
**Out of scope**: distributing patched One UI images, commercial distribution of vendor protocol details, manufacturing replacement hardware.

## Success criteria

- Discover a repeatable USB command sequence that reliably changes fan speed, or
- Implement a safe MCU bypass that provides equivalent fan control.
- Provide a documented, reproducible procedure and a tested Android app or hardware schematic.
- Safety checks and emergency stop procedures are in place and tested.

## Constraints

- Personal use only; avoid distribution that violates legal constraints.
- Non‑destructive testing first; IN (read) probes only until protocol is understood.
- Work primarily in Kotlin; VS Code or Android Studio acceptable.

## Stakeholders

- Project maintainer, test operators, and any collaborators.

**Date created**: 2026-04-13
