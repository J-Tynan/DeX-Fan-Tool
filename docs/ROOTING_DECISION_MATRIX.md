# Rooting Decision Matrix

Use this document before any irreversible step such as bootloader unlocking, rooting a phone, or spending materially more time on a path that may not produce a usable end product.

## Purpose

- Decide whether to root the Galaxy S9 `SM-G960F` now, hold for one more round of reversible tests, or stop treating rooted capture as the primary path.
- Separate research value from deployment value. Root for protocol discovery does not automatically imply a rooted end product.
- Keep the project's end-product goal explicit: a practical fan-control solution, ideally non-root on compatible devices, but not at the cost of ignoring hard evidence.

## Decision outputs

- `GO`: root the Galaxy S9 now.
- `HOLD`: do one more round of reversible validation first.
- `NO-GO`: do not root now; pivot or stop this path.

## Hard blockers

If any of these are true, do not root yet.

1. The Galaxy S9 does not remain stable after the verified OEM-unlock path and pre-root intake.
2. The exact Galaxy S9 model / region is not realistically rootable with packages that match `starlte` / `SM-G960F`.
3. The test operator is not willing to lose the Galaxy S9 as a normal-use device.
4. A full backup and recovery plan is not ready.
5. The only acceptable project outcome is a broad public non-root app for most users.

## Scoring method

Score each criterion as `0`, `1`, or `2`.

- `0`: weak, unknown, or unfavorable
- `1`: mixed or incomplete
- `2`: strong or favorable

Multiply each score by the criterion weight, then sum the weighted values.

## Weighted matrix

| Criterion | Weight | 0 | 1 | 2 |
| --- | ---: | --- | --- | --- |
| S9 rooted-target readiness | 3 | Unstable or unverified | Partly validated | Stable, intake-complete, and unlock path verified |
| Root feasibility | 3 | Variant likely blocked | Possible but uncertain | Known, practical root path |
| Capture value | 3 | Root likely adds little | Some information gain | High chance of protocol-defining traces |
| Modern non-root viability | 3 | Stock phones likely cannot access dock | Unknown | Stock phones likely expose enough USB access |
| End-product flexibility | 2 | Only broad non-root app counts | Narrow support maybe acceptable | Rooted research or limited-device success is acceptable |
| Reversible testing completeness | 2 | Many reversible checks still undone | Some done, key gaps remain | Most reversible checks already exhausted |
| Time budget and motivation | 2 | Low tolerance for more weeks | Moderate tolerance | Comfortable investing more time |
| Alternative path quality | 1 | Better alternatives already available | Alternatives unclear | Rooting is the best next information source |

Maximum score: `38`

## Decision thresholds

| Total score | Decision | Meaning |
| ---: | --- | --- |
| `28-38` | `GO` | Rooting is justified now. |
| `19-27` | `HOLD` | Rooting is promising but not yet the best next step. |
| `0-18` | `NO-GO` | Do not root now; pivot or stop this path. |

## Current provisional score

This is a starting estimate only. Re-score after each major validation step.

| Criterion | Weight | Provisional score | Weighted |
| --- | ---: | ---: | ---: |
| S9 rooted-target readiness | 3 | 2 | 6 |
| Root feasibility | 3 | 1 | 3 |
| Capture value | 3 | 2 | 6 |
| Modern non-root viability | 3 | 1 | 3 |
| End-product flexibility | 2 | 2 | 4 |
| Reversible testing completeness | 2 | 2 | 4 |
| Time budget and motivation | 2 | 1 | 2 |
| Alternative path quality | 1 | 2 | 2 |

Estimated total: `30`

Current provisional decision: `GO`

## Interpretation

- The project still appears viable and the S9 now looks like the strongest next rooted-discovery path.
- Rooting the S9 likely has real research value even though its stock dock behavior still leaves the fan silent.
- Exact recovery / rooting package mapping for this `SM-G960F` still needs to be assembled before any flash step, so root feasibility is promising rather than fully closed.
- The main unresolved issue is no longer root feasibility for the working candidate; it is whether rooted capture on the S9 reveals the missing host behavior behind the silent-fan path.
- Success is more likely to mean a practical but constrained solution than a universal consumer app.

## Current model-specific note

- The primary rooted-capture candidate is now `SM-G960F` on build `QP1A.190711.020.G960FXXUHFVB4`.
- Verified post-unlock evidence shows `ro.boot.flash.locked=0`, `ro.boot.verifiedbootstate=orange`, and warranty bit `0` after the OEM-unlock wipe / reboot flow.
- Public support signals now line up with this exact family: the official LineageOS `starlte` device page lists `SM-G960F`, `SM-G960F/DS`, and `SM-G960N`, and the official TWRP Galaxy S9 (Exynos) page is current for codename `starlte`.
- Public Samsung boot-mode references are available for this family: Download Mode is `Volume Down + Bixby + Power`, and Recovery is `Volume Up + Bixby + Power` from power-off.
- The official TWRP page explicitly warns that this device uses dm-verity and that stock ROM behavior can replace custom recovery on first boot unless recovery is entered immediately after flashing.
- Stock dock behavior on this S9 matches the S23 Ultra for charge, HDMI, and DeX, but the fan still stays silent. That makes it a useful rooted silent-path capture candidate.
- The older Note 9 `SM-N960U1` remains valuable only as a stock fan-positive baseline because its exact U.S. U1 variant still does not have a demonstrated practical root path in hand.

## Evidence that moves the decision to GO

Any three of these would likely move the score into `GO` territory.

1. Confirm the S9 remains stable after the OEM-unlock reboot and repeated dock use.
2. Confirm the exact S9 model / region has a practical root package path in hand.
3. Complete the modern-phone visibility gate with a known-good charger, cable, and physical fit.
4. Confirm that a narrower end product is still acceptable if universal support is not realistic.
5. Confirm that a hardware analyzer would cost more time or money than the S9 root path.

## Evidence that moves the decision to NO-GO

Any one of these should trigger serious reconsideration.

1. The S9 becomes unstable, unreliable, or no longer reaches consistent DeX behavior after the unlock step.
2. The exact S9 variant is impractical to root or too risky to sacrifice.
3. Modern stock phones clearly cannot see or claim the dock in usable ways, and a limited or hardware-assisted outcome is not acceptable.
4. The only acceptable outcome is a broad public non-root app, and the evidence starts pointing strongly away from that.
5. Time budget or motivation no longer supports a multi-stage research project.

## Immediate pre-root checklist

Before any rooting step, complete these reversible checks.

1. Confirm the S9 exact model, region, Android version, and One UI version.
2. Confirm the S9 still produces the known stock dock behavior: charge, HDMI, DeX, fan silent.
3. Confirm wireless or USB `adb` remains reliable enough to support a controlled root session.
4. Confirm backup and recovery for the S9.
5. Keep the Note 9 stock fan-positive baseline available for later comparison.
6. Decide whether the project still has value if the end product becomes a limited-device app, rooted-only tool, or hardware fallback.

## Relationship to end-product goals

- Rooting for capture may still support a later non-root app if compatible stock devices expose enough USB access and public thermal signals.
- The likely support target is a compatible subset of Samsung / DeX-capable devices, not every Android phone.
- If stock-device USB access proves insufficient, the fallback remains rooted-only tooling or the documented hardware bypass path.

## Related documents

- `docs/HARDWARE_INVENTORY.md`
- `docs/TEST_PLAN.md`
- `docs/S9_PRE_FLASH_CHECKLIST.md`
- `docs/S9_BACKUP_AND_RECOVERY_CHECKLIST.md`
- `docs/RISK_AND_SAFETY.md`
- `docs/SCOPE.md`
