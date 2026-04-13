from __future__ import annotations

import argparse
import json
from pathlib import Path


def parse_args() -> argparse.Namespace:
    parser = argparse.ArgumentParser(
        description="Preview a recorded control-transfer sequence before implementing replay."
    )
    parser.add_argument("sequence", type=Path, help="Path to a JSON file describing replay steps")
    return parser.parse_args()


def main() -> int:
    args = parse_args()
    payload = json.loads(args.sequence.read_text(encoding="utf-8"))
    steps = payload.get("steps", [])

    print(f"Loaded {len(steps)} step(s) from {args.sequence}")
    for index, step in enumerate(steps, start=1):
        request = step.get("request", "?")
        value = step.get("value", "?")
        data = step.get("data", "")
        print(f"{index:02d}: request={request} value={value} data={data}")

    return 0


if __name__ == "__main__":
    raise SystemExit(main())
