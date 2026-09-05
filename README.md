# WildFly Builder

Prototype software factory that generates WildFly / Java EE apps from a shared
template, metadata, and Groovy extenders.

Part of the SW-Builder factories for rapid Java EE / WildFly app development.

Catalog: https://sw-builder.com/appstore/builders/apps/wildfly-builder.html

## What it does

`template/` is a base WildFly application framework.

`build/` holds reusable fragments declared in XML:

- `APP_APPS.xml` — application / module wiring
- `APP_CODES.xml` — code fragments
- `APP_EVENT_CODES.xml` — event handlers
- `APP_FUNCS.xml` — functions
- `APP_PROCS.xml` — procedures

An app-specific extender selects and applies those fragments onto the template.

Think of a 3D printer: small pieces of code are fused onto the template to
produce a deployable WildFly app.

## Layout

```text
wildfly-builder/
├── WildFlyBuilder.groovy    # factory entry point
├── updateAppsList.groovy    # refreshes the known-apps list
├── update_and_commit.sh     # overlay + commit helper
├── build/
│   ├── APP_APPS.xml
│   ├── APP_CODES.xml
│   ├── APP_EVENT_CODES.xml
│   ├── APP_FUNCS.xml
│   └── APP_PROCS.xml
└── template/                # base WildFly project
```
## How a build runs
Build scripts live in each generated app repo, not in this builder.
Example:
```text
~/wildfly/wildfly-booklet-app/wildfly-booklet-build-deploy.sh
```
## That script typically:

1. Pulls this builder

2. Overlays template/ and build/ onto the app

3. Runs update_and_commit.sh

4. Runs WildFlyBuilder and an app-specific extender
(e.g. BookletExtender)
