#!/usr/bin/env bash
set -euo pipefail

# --- Configuration --------------------------------
GROOVY_SCRIPT="./updateAppsList.groovy"
APP_FILE="build/APP_APPS.xml"
APP_SHARE_FILE="../share/APP_APPS.xml"
HOME_FILE="options/APP_HOME.xml"
SHARE_DIR="../share"
BUILD_DIR="./build"
GFX_DIR="./template/xxxxx-war/src/main/webapp/resources/gfx"
# -------------------------------------------------

if [[ ! -d "$SHARE_DIR" ]]; then
    echo "Share dir '$SHARE_DIR' not found – creating it"
    mkdir -p "$SHARE_DIR"
fi

if [[ ! -f "$APP_SHARE_FILE" ]]; then
    if [[ ! -f "$APP_FILE" ]]; then
        echo "Error: empty APP_APPS.xml not found at '$APP_FILE'" >&2
        exit 1
    fi
    echo "Moving empty APP_APPS.xml from '$APP_FILE' to '$APP_SHARE_FILE'"
    mv "$APP_FILE" "$APP_SHARE_FILE"
fi

# Safely get the one PNG file
shopt -s nullglob
PNG_FILES=(*.png)
shopt -u nullglob

if [[ ${#PNG_FILES[@]} -ne 1 ]]; then
    echo "Error: Expected exactly one .png file, found ${#PNG_FILES[@]}" >&2
    echo "Found: ${PNG_FILES[*]}" >&2
    exit 1
fi

PNG_FILE="${PNG_FILES[0]}"
cp "$PNG_FILE" "$SHARE_DIR/"

shopt -s nullglob
SHARE_PNGS=("$SHARE_DIR"/*.png)
shopt -u nullglob

if [[ ${#SHARE_PNGS[@]} -eq 0 ]]; then
    echo "Error: no PNG files found in $SHARE_DIR" >&2
    exit 1
fi

mkdir -p "$GFX_DIR"
cp "${SHARE_PNGS[@]}" "$GFX_DIR/"

if [[ -f "$APP_SHARE_FILE" ]]; then
    echo "File '$APP_SHARE_FILE' exists."
else
    echo "File '$APP_SHARE_FILE' does not exist."
    exit 1
fi

mkdir -p "$BUILD_DIR"
cp "$APP_SHARE_FILE" "$BUILD_DIR"

echo "START"
OUTPUT=$(groovy "$GROOVY_SCRIPT" 2>&1)
echo "$OUTPUT"

if echo "$OUTPUT" | grep -Fq "APP_APPS.xml updated"; then
    echo ">>> APP_APPS.xml was updated – copy to Share"
    cp "$APP_FILE" "$APP_SHARE_FILE"
else
    echo ">>> APP_APPS.xml up-to-date "
fi