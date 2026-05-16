# Samsung DeX Listing Review Template

Use this document when real listings start appearing. Copy one review block per listing and keep the wording factual so multiple candidate phones can be compared quickly.

## How to use

- Start with the exact model number, not the marketing name.
- Reject the listing immediately if it is clearly `U`, `U1`, carrier-locked, MDM-locked, FRP-locked, blacklisted, or sold as parts only.
- Prefer short factual notes over long commentary.
- If a listing survives the first pass, give it a final outcome: `buy now`, `watch`, `ask seller`, or `reject`.

## Outcome meanings

- `buy now`: exact target model, clean lock status, acceptable price, no major USB-C warning signs.
- `watch`: plausible target, but price or seller response is not good enough yet.
- `ask seller`: listing might be good, but the model, lock status, or USB-C condition is still unclear.
- `reject`: wrong variant or too many risk flags.

## Blank review block

```md
### Listing review: [short title]

- Date reviewed:
- Platform:
- Seller location:
- Listing URL:
- Asking price:
- Model claimed in listing:
- Exact model confirmed:
- Preferred target tier: S9 / S9+ / Note9 / Note8 / S10e / S10

#### Fast-pass result

- Exact target model present: yes / no / unclear
- U.S. `U` / `U1` warning: yes / no / unclear
- Lock or blacklist warning: yes / no / unclear
- USB-C condition visible: good / mixed / bad / unclear
- Screen condition: good / mixed / bad / unclear
- Local collection practical: yes / no / maybe

#### Seller-confirmed details

- IMEI clear / not blacklisted: yes / no / unclear
- Factory reset complete: yes / no / unclear
- Google or Samsung lock absent: yes / no / unclear
- Finance or MDM lock absent: yes / no / unclear
- USB-C charges normally: yes / no / unclear
- USB-C port feels secure: yes / no / unclear
- HDMI or DeX tested: yes / no / unclear
- Battery note: normal / weak / swollen / unknown

#### Evidence seen

- Settings photo with model number: yes / no
- USB-C close-up photo: yes / no
- Powered-on screen photo: yes / no
- Download Mode photo: yes / no

#### Scorecard

| Item | Good / Mixed / Bad | Note |
| --- | --- | --- |
| Exact model |  |  |
| USB-C condition |  |  |
| Lock status |  |  |
| Price |  |  |
| Travel / collection |  |  |

#### Decision

- Outcome: buy now / watch / ask seller / reject
- Reason:
- Next action:
```

## Short comparison table

Use this when several listings are live at once.

| Listing | Model | Price | Area | Exact model confirmed | USB-C risk | Lock risk | Verdict |
| --- | --- | --- | --- | --- | --- | --- | --- |
| Example A | `SM-G960F` | GBP ___ | local collection | yes | low | low | watch |
| Example B | `SM-N960F` | GBP ___ | regional seller | no | unclear | unclear | ask seller |
| Example C | `SM-G960U` | GBP ___ | eBay post | yes | unknown | high | reject |

## Suggested file hygiene

- Keep this file as the template.
- For real marketplace work, duplicate the structure into a dated note under `data/` only after removing personal seller details that should not be retained.
- If a phone is actually purchased, update `docs/HARDWARE_INVENTORY.md` and the procurement task in `TODO.md`.