# Rooting Decision Matrix

Use this document before any irreversible step such as bootloader unlocking, rooting a phone, or spending materially more time on a path that may not produce a usable end product.

## Purpose

- Decide whether to root the Galaxy Note 9 now, hold for one more round of reversible tests, or stop treating rooting as the primary path.
- Separate research value from deployment value. Root for protocol discovery does not automatically imply a rooted end product.
- Keep the project's end-product goal explicit: a practical fan-control solution, ideally non-root on compatible devices, but not at the cost of ignoring hard evidence.

## Decision outputs

- `GO`: root the Note 9 now.
- `HOLD`: do one more round of reversible validation first.
- `NO-GO`: do not root now; pivot or stop this path.

## Hard blockers

If any of these are true, do not root yet.

1. The Note 9 does not reliably trigger DeX Station fan behavior in stock form.
2. The exact Note 9 model / region is not realistically rootable.
3. The owner is not willing to lose the Note 9 as a normal-use device.
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
| Note 9 baseline behavior | 3 | Fan not confirmed | Partial or inconsistent | Reliably drives fan |
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
| Note 9 baseline behavior | 3 | 1 | 3 |
| Root feasibility | 3 | 1 | 3 |
| Capture value | 3 | 2 | 6 |
| Modern non-root viability | 3 | 1 | 3 |
| End-product flexibility | 2 | 2 | 4 |
| Reversible testing completeness | 2 | 0 | 0 |
| Time budget and motivation | 2 | 1 | 2 |
| Alternative path quality | 1 | 2 | 2 |

Estimated total: `23`

Current provisional decision: `HOLD`

## Interpretation

- The project still appears viable, but not yet ripe enough for an irreversible step.
- Rooting the Note 9 likely has real research value.
- The main unresolved issue is still whether a stock modern phone can access the dock well enough for a future non-root app.
- Success is more likely to mean a practical but constrained solution than a universal consumer app.

## Evidence that moves the decision to GO

Any three of these would likely move the score into `GO` territory.

1. Confirm the Note 9 reliably triggers dock fan behavior.
2. Confirm the exact Note 9 model / region has a practical root path.
3. Complete the modern-phone visibility gate with a known-good charger, cable, and physical fit.
4. Confirm that a narrower end product is still acceptable if universal support is not realistic.
5. Confirm that a hardware analyzer would cost more time or money than the Note 9 root path.

## Evidence that moves the decision to NO-GO

Any one of these should trigger serious reconsideration.

1. The Note 9 does not actually drive the fan.
2. The exact Note 9 variant is impractical to root or too risky to sacrifice.
3. Modern stock phones clearly cannot see or claim the dock in usable ways, and a limited or hardware-assisted outcome is not acceptable.
4. The only acceptable outcome is a broad public non-root app, and the evidence starts pointing strongly away from that.
5. Time budget or motivation no longer supports a multi-stage research project.

## Immediate pre-root checklist

Before any rooting step, complete these reversible checks.

1. Confirm the Note 9 exact model, region, Android version, and One UI version.
2. Confirm the Note 9 still produces known-good dock behavior.
3. Confirm the modern phone visibility gate using the best available charger, cable, and fit conditions.
4. Confirm backup and recovery for the Note 9.
5. Decide whether the project still has value if the end product becomes a limited-device app, rooted-only tool, or hardware fallback.

## Relationship to end-product goals

- Rooting for capture may still support a later non-root app if compatible stock devices expose enough USB access and public thermal signals.
- The likely support target is a compatible subset of Samsung / DeX-capable devices, not every Android phone.
- If stock-device USB access proves insufficient, the fallback remains rooted-only tooling or the documented hardware bypass path.

## Related documents

- `docs/HARDWARE_INVENTORY.md`
- `docs/TEST_PLAN.md`
- `docs/RISK_AND_SAFETY.md`
- `docs/SCOPE.md`
