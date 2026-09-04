# WildFly Builder

Prototype. Groovy factory that stamps WildFly apps from metadata and templates.

WildFly application factory with Groovy templates, metadata-driven generation,
and extenders. Core of the SW-Builder software factories for rapid Java EE /
WildFly app development.

Catalog: https://sw-builder.com/appstore/builders/apps/wildfly-builder.html

## Layout

```text
wildfly-builder/
├── WildFlyBuilder.groovy
├── updateAppsList.groovy
├── update_and_commit.sh
├── build/
│   ├── APP_APPS.xml
│   ├── APP_CODES.xml
│   ├── APP_EVENT_CODES.xml
│   ├── APP_FUNCS.xml
│   └── APP_PROCS.xml
└── template/
```
## Build scripts

Build/deploy scripts live in each generated app repo, not in this builder.

Example:

    ~/wildfly/wildfly-booklet-app/wildfly-booklet-build-deploy.sh

That script pulls this builder, overlays `template/` and `build/` onto the app,
runs `update_and_commit.sh`, then `WildFlyBuilder` / `BookletExtender`.
