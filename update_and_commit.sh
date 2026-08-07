# --- Configuration --------------------------------
GROOVY_SCRIPT="./updateAppsList.groovy"
APP_FILE="build/APP_APPS.xml"
APP_SHARE_FILE="../Share/APP_APPS.xml"
HOME_FILE="options/APP_HOME.xml"
SHARE_DIR="../Share"
BUILD_DIR="./build"
GFX_DIR="./template/xxxxx-war/src/main/webapp/resources/gfx"
# -------------------------------------------------

# Safely get the one PNG file
shopt -s nullglob  # Make globs expand to nothing if no match (optional safety)
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

cp "${SHARE_PNGS[@]}" "$GFX_DIR/"

# Check if the APP_SHARE_FILE exists
if [ -f "$APP_SHARE_FILE" ]; then
    echo "File '$APP_SHARE_FILE' exists."
else
    echo "File '$APP_SHARE_FILE' does not exist."
    exit 1 
fi

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