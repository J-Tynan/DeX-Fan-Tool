# Galaxy S9 `starlte` usbmon ROM/kernel candidate summary

## Current baseline

- Device: Samsung Galaxy S9 `SM-G960F` (`starlte`)
- Current confirmed state: rooted stock Android 10 / One UI 2.5 with working Magisk root
- Runtime gate result on rooted stock kernel:
  - `debugfs` available
  - `/sys/kernel/debug/usb/usbmon` absent
  - kernel config shows `CONFIG_DEBUG_FS=y`
  - kernel config shows `# CONFIG_USB_MON is not set`

This means the current rooted stock kernel is a hard stop for `usbmon` capture.

## ROM/kernel source checks completed

### 1. Official LineageOS `starlte` path

Findings:

- LineageOS device support exists for `starlte`, but the official kernel branch inspected was still missing `USB_MON`.
- Repo: `LineageOS/android_kernel_samsung_exynos9810`
- Branch: `lineage-20`
- Checked file: `arch/arm64/configs/exynos9810-starlte_defconfig`
- Result:
  - `CONFIG_DEBUG_FS=y`
  - `# CONFIG_USB_MON is not set`

Conclusion:

- A plain, unmodified LineageOS flash is not a credible `usbmon` solution by itself.

### 2. Public Exynos9810 kernel forks with visible `starlte` configs

Checked candidates:

- `ExyHyperBrick/android_kernel_samsung_exynos9810`
  - visible config path: `arch/arm64/configs/exynos9810-starlte_defconfig`
  - no visible `CONFIG_USB_MON` setting found during inspection
- `TheUnrealZaka/exynos9810-kernel`
  - visible config path: `arch/arm64/configs/starlte_defconfig`
  - no visible `CONFIG_USB_MON` setting found during inspection

Current interpretation:

- These are not yet credible `usbmon` candidates.
- No public evidence was found that they expose `/sys/kernel/debug/usb/usbmon/0u` on `starlte`.

### 2.5. Official Kali NetHunter `starlte` path

Findings:

- Kali NetHunter live support indexes list the Galaxy S9 `starlte` as both:
  - an official supported device module
  - a pre-created image target for `starlte-los` / LineageOS 20
- The indexed kernel source points to `V0lk3n/nethunter_kernel_samsung_exynos9810` on branch `nethunter-lineage-20`.
- Checked file: `arch/arm64/configs/exynos9810-starlte_defconfig`
- Result:
  - `CONFIG_DEBUG_FS=y`
  - `# CONFIG_USB_MON is not set`

Additional `starlte` NetHunter-style repos checked:

- `Tkpointz/exynos9810_starlte_kernel`
  - checked `arch/arm64/configs/kali_starlte_defconfig`
  - checked `arch/arm64/configs/exynos9810-starlte_defconfig`
  - both still had `# CONFIG_USB_MON is not set`
- `ouroboros420/android_kernel_samsung_exynos9810`
  - checked `arch/arm64/configs/exynos9810-starlte_defconfig`
  - still had `# CONFIG_USB_MON is not set`

Conclusion:

- Official NetHunter support for the S9 is real, but current public `starlte` NetHunter kernel sources checked so far are still not credible `usbmon` paths.
- NetHunter support and `usbmon` support are not the same thing for this device.

### 3. Mainline-style `starlte` repo

Checked candidate:

- `ruslanbay/exynos9810-starlte`

Findings:

- This appears to be a mainline Linux bring-up effort, not a ready Android ROM or prebuilt flash path for this project.
- It may be useful for long-term kernel work, but it is not currently a practical near-term route to DeX fan-control capture.

## Working conclusion

No credible prebuilt or near-stock public `starlte` ROM/kernel candidate has been confirmed so far with `CONFIG_USB_MON` enabled.

The strongest current conclusion is:

- stock rooted Samsung kernel: fails `usbmon`
- official LineageOS kernel for `starlte`: also fails `usbmon` at config level
- official/public NetHunter `starlte` kernels checked so far: also fail `usbmon` at config level
- public fork inspection so far: no confirmed `USB_MON`-enabled candidate

## Best next path

If the project stays on the S9, the most realistic technical path is now a custom kernel build that starts from a known `starlte`-compatible source tree and explicitly enables `CONFIG_USB_MON`.

Most likely starting points:

- downstream Samsung source matching the exact S9 firmware family
- or the official LineageOS Exynos9810 kernel tree with an explicit config change

## Practical constraint

The user explicitly does not want a Linux build host right now.

That makes the likely decision tree:

1. Keep searching only for high-evidence prebuilt S9 kernels/ROMs that explicitly enable `USB_MON`.
2. If no such candidate appears quickly, treat S9 `usbmon` as requiring a custom build pipeline.
3. If a custom build pipeline is also unacceptable, pivot to another device with a more accessible `usbmon` path.