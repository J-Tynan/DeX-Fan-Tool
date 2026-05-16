# Samsung DeX Replacement Phone Shortlist

Use this document after the locally tested `SM-N960U1` Galaxy Note 9 path is treated as blocked for root-focused capture work. The goal is not to build a perfect Samsung compatibility database. The goal is to buy the cheapest Samsung DeX phone that is still useful for this project and has a materially better chance of bootloader unlocking or rooting than a U.S. carrier-style Snapdragon variant.

## Purpose

- Preserve the 2026-05-16 replacement-device research in the repository.
- Keep the buying decision tied to the project goal: deeper capture against the original `EE-MG950` dock, not just general Samsung DeX use.
- Favor exact model numbers that are more plausible root candidates in the UK used market.

## Working conclusion

- Prefer older international Samsung models, especially European Exynos variants.
- Avoid U.S. `U` / `U1` Samsung variants when rootability matters.
- For this project, the best first targets are dock-era phones from 2017 to 2019, not newer flagships.

## Why Exynos is highlighted

- Older Exynos Samsung phones generally have a clearer public history of bootloader unlock and aftermarket recovery work than U.S. Snapdragon `U` / `U1` variants.
- UK second-hand listings are more likely to surface the European variants this project actually wants.
- The blocked local phone is the cautionary example: `SM-N960U1` is useful as a stock DeX baseline, but its exact variant does not currently present a practical root path.

## Conservative DeX-capable phone list

This is a procurement-oriented summary, not a promise that every regional sub-variant is equally useful.

| Year | Samsung phone families worth knowing about | Procurement note |
| --- | --- | --- |
| 2017 | Galaxy S8, S8+, Note8 | First DeX phone generation and closest to the original dock era. |
| 2018 | Galaxy S9, S9+, Note9 | Strongest fit for this project because they sit in the same generation window as the dock. |
| 2019 | Galaxy S10e, S10, S10+, S10 5G, Note10 family, Galaxy Fold, Galaxy A90 5G | Still useful, but usually not as cheap as the 2017-2018 phones. |
| 2020 | Galaxy S20 family, Note20 family, Z Fold2 | DeX-capable, but already moving away from the original dock era. |
| 2021 | Galaxy S21 family, Z Fold3 | Likely less attractive as root research buys than older Exynos phones. |
| 2022 | Galaxy S22 family, Z Fold4, XCover6 Pro | Good DeX devices, but usually the wrong price-to-research-value ratio here. |
| 2023 | Galaxy S23 family, Z Fold5 | Useful as modern comparison phones, not as first purchase targets. |
| 2024 | Galaxy S24 family | Treat as modern comparison hardware, not a budget capture target. |
| 2025 | Galaxy S25 family | Same guidance as S24: valuable for comparison, poor as a first procurement choice. |

## Exact-model shortlist

These are the best first purchase targets for this project.

| Priority | Exact model targets | Why they are attractive | Relative UK used-cost tier | Main caution |
| --- | --- | --- | --- | --- |
| 1 | `SM-G960F`, `SM-G965F` | Galaxy S9 / S9+ Exynos models are old enough to be cheap, new enough to be practical, and closely aligned with the DeX dock generation. | Lowest useful tier | Check for worn USB-C ports and tired batteries. |
| 2 | `SM-N960F` | Exynos Galaxy Note9 is the closest replacement for the locally blocked `SM-N960U1`, while still matching the generation that already proved fan-triggering behavior on stock firmware. | Low-to-mid tier | More expensive than S9-class devices and often sold with OLED burn. |
| 3 | `SM-N950F` | Exynos Galaxy Note8 is still dock-era hardware and can be a cheap fallback if Note9 prices are poor. | Lowest useful tier | Older battery age and weaker long-term reliability. |
| 4 | `SM-G970F`, `SM-G973F` | Galaxy S10e / S10 Exynos models provide a slightly newer comparison point without jumping all the way to current locked-down flagships. | Mid tier | Usually worse value than S9 unless you specifically want a newer baseline. |

## Model-specific aftermarket and bootloader notes

Use this section to separate phones that are merely DeX-capable from phones that also have visible public aftermarket support.

- The strongest positive signal in this document is public LineageOS or official TWRP support for the exact Exynos-family target model.
- That is still not a guarantee that any individual used handset will have `OEM unlocking` available today.
- Treat these notes as screening guidance, not as a promise that a listing is root-ready.

| Model target | Codename | Public aftermarket signal | Bootloader / recovery signal | Project read |
| --- | --- | --- | --- | --- |
| `SM-G960F` | `starlte` | LineageOS device and install pages exist for `SM-G960F`, `SM-G960F/DS`, and `SM-G960N`. Official TWRP page exists for Galaxy S9 (Exynos). | Public install docs explicitly require an exact model match, USB debugging, FRP cleanup, Samsung Download Mode, and custom recovery flashing. | Best first buy: cheap, dock-era, and clearly represented in public recovery / ROM documentation. |
| `SM-G965F` | `star2lte` | LineageOS device page exists for `SM-G965F`, `SM-G965F/DS`, and `SM-G965N`. | Public install flow and Samsung Download Mode path are documented through the LineageOS device page family. | Almost as good as `SM-G960F`; buy if the listing is cleaner or cheaper than the smaller S9. |
| `SM-N960F` | `crownlte` | LineageOS device and install pages exist for `SM-N960F`, `SM-N960F/DS`, and `SM-N960N`. Official TWRP page exists for Galaxy Note 9 (Exynos). | Public docs show the same Samsung Download Mode and custom recovery path, with explicit dm-verity caveats on the TWRP side. | Strongest Note-line target because it is closest to the locally proven fan-triggering generation without repeating the blocked `SM-N960U1` mistake. |
| `SM-N950F` | `greatlte` | Official TWRP page exists for Galaxy Note 8 (Exynos) with current support status. | TWRP documents Odin flashing, download mode entry, and a dm-verity warning. | Good fallback when price matters most, but older age and wear make it a weaker main target than S9 or Note9. |
| `SM-G970F` | `beyond0lte` | Current LineageOS device and install pages exist for `SM-G970F`, `SM-G970F/DS`, and `SM-G970N`. | Public install docs require exact model matching, FRP cleanup, and a Samsung-specific recovery / Download Mode flow. Current LineageOS support is a stronger maintenance signal than on the older S9 era. | Best newer comparison device if you want active aftermarket support more than lowest possible cost. |
| `SM-G973F` | `beyond1lte` | Current LineageOS device and install pages exist for `SM-G973F`, `SM-G973F/DS`, and `SM-G973N`. | Same Samsung-specific install prerequisites as `SM-G970F`, including exact model checks and vendor firmware requirements. | Useful if you want a newer Exynos baseline, but usually not the best value for first procurement. |

## Practical unlock cautions

- Exact model match still matters more than marketing name. `Galaxy S9` is not enough; `SM-G960F` is what matters.
- Remove all Google accounts before any bootloader or recovery work to avoid Factory Reset Protection problems.
- Samsung aftermarket flows here are recovery-and-download-mode centric, not modern Pixel-style fastboot flows.
- TWRP pages for these Exynos families explicitly warn about dm-verity and stock ROM recovery replacement behavior.
- A seller phone can still be a bad candidate if `OEM unlocking` is missing, the device is MDM-managed, or the firmware state is unusual.

## Buy rules

- Search by exact model number first, then by marketing name.
- Prefer listings that show the model in Settings, Download Mode, or on the retail label.
- Prioritize a clean USB-C port, known-good charging, and confirmed display output over cosmetic condition.
- Avoid `U`, `U1`, carrier-branded, finance-locked, MDM-locked, or FRP-problem devices.
- Avoid listings that only say `Galaxy Note 9` or `Galaxy S9` without the exact model.
- If choosing between a cheaper cracked-screen phone and a cleaner phone, prefer the cleaner USB-C port and verified HDMI output.

## UK buying notes

- Local collection can be useful when the seller will let you inspect the USB-C port before paying.
- Start with `eBay UK`, `Facebook Marketplace`, `Gumtree`, `CeX`, and local second-hand electronics shops.
- Use search terms like `SM-G960F`, `SM-G965F`, `SM-N960F`, `SM-N950F`, `SM-G970F`, and `SM-G973F` rather than generic `Samsung DeX phone` searches.
- Live storefront scraping was blocked during this session, so treat the cost guidance in this document as relative ranking rather than a quoted market snapshot.
- In practice, expect `S9` / `S9+` and `Note8` class devices to be the cheapest commonly useful tier, `Note9` to come next, and `S10e` / `S10` to cost more.

## Seller screening checklist

Use this checklist on each listing before spending time on price negotiation.

### Fast pass

- [ ] Listing includes one of the exact target model numbers: `SM-G960F`, `SM-G965F`, `SM-N960F`, `SM-N950F`, `SM-G970F`, or `SM-G973F`.
- [ ] Listing does not mention `U`, `U1`, Verizon, AT&T, T-Mobile, Sprint, carrier lock, finance lock, MDM, or FRP problems.
- [ ] Listing photos show a phone that is complete enough to test: powered on, screen visible, and USB-C area not obviously damaged.
- [ ] Price is plausible for the model tier and condition rather than suspiciously low.

### Ask the seller for confirmation

- [ ] Exact model number from Settings or the rear label.
- [ ] IMEI status or confirmation that the phone is not blacklisted.
- [ ] Confirmation that the USB-C port charges normally and holds a cable securely.
- [ ] Confirmation that video output or Samsung DeX has worked, if the seller has tested it.
- [ ] Confirmation that the phone is factory reset and not Google-locked or Samsung-locked.
- [ ] Battery health description: normal, drains quickly, swollen, unknown.

### Ask for photos if missing

- [ ] Settings screen showing the exact model number.
- [ ] Lock screen or home screen proving the display works cleanly enough for testing.
- [ ] Close photo of the USB-C port.
- [ ] Photo of the back glass and frame corners.
- [ ] Optional but useful: Download Mode screen if the seller is technical enough to provide it.

### Reject immediately if any of these appear

- [ ] Model is `U`, `U1`, or otherwise clearly a U.S. Snapdragon carrier-style variant.
- [ ] Seller cannot provide the exact model number.
- [ ] USB-C port is loose, intermittent, corroded, or described as charge-only.
- [ ] Phone is account-locked, finance-locked, blacklisted, or sold for parts only.
- [ ] Battery is swollen or the phone overheats during basic charging.

### Prefer locally if possible

- [ ] Meet in a place where the phone can be powered on and inspected.
- [ ] Check that the USB-C port is physically clean and the connector feels stable.
- [ ] Confirm IMEI and model before paying.
- [ ] If the seller allows it, verify charging with a known-good USB-C cable.

## Quick listing scorecard

Use this when comparing several listings quickly.

| Score item | Good | Mixed | Bad |
| --- | --- | --- | --- |
| Exact model | Target Exynos model clearly shown | Claimed but not shown | Wrong or unknown model |
| USB-C condition | Clean and confirmed working | Untested or unclear | Loose, damaged, or intermittent |
| Lock status | Factory reset and unlocked | Seller unsure | FRP, MDM, or finance issues |
| Price | Fits model and condition | Slightly high | Too high or suspiciously cheap |
| Travel / collection | Local or posted by a reputable seller | Moderate travel or vague seller | High-friction collection or evasive seller |

Prefer listings with mostly `Good` and no `Bad` in the first three rows.

## Seller message template

Use this as a copy-and-edit message for Marketplace, Gumtree, or eBay questions.

```text
Hi, I am interested in the phone. Before I buy, can you please confirm:

1. The exact model number shown in Settings or on the label.
2. Whether the USB-C port charges normally and feels secure.
3. Whether the phone is fully reset with no Google, Samsung, finance, or MDM lock.
4. Whether you can send one photo of the model number screen and one close photo of the USB-C port.

If all of that looks good, I should be able to decide quickly. Thanks.
```

## Recommended buy order

1. Buy an Exynos `SM-G960F` or `SM-G965F` first.
2. If the project still needs Note-line parity, buy an Exynos `SM-N960F` next.
3. Use `SM-N950F` only if the Note9 price is poor or local supply is bad.
4. Use `SM-G970F` or `SM-G973F` only if you want a newer comparison phone after the older dock-era path is explored.

## Relationship to the current local hardware

- Keep the local `SM-N960U1` as the stock baseline device because it already proves that the dock fan can still be triggered by an older Samsung phone.
- Do not treat that exact `SM-N960U1` as the primary root target unless a specific U / U1 unlock path is later found.
- Use the replacement-device shortlist above to reduce time wasted on variants that are likely to be carrier-locked dead ends.

## Related documents

- `docs/HARDWARE_INVENTORY.md`
- `docs/DEX_LISTING_REVIEW_TEMPLATE.md`
- `docs/ROOTING_DECISION_MATRIX.md`
- `docs/TEST_PLAN.md`
- `data/2026-05-16-s23-ultra-read-only-in-probe-summary.md`