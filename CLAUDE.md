# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Commands

```bash
./gradlew build           # Compile and package the plugin JAR
./gradlew assemblePlugin  # Copy built JAR into the configured plugins directory
```

## Architecture

The simplest reference plugin in the workspace — useful as a template for new plugins.

Everything lives in `lib/src/main/kotlin/dk/holonet/clock/ClockPlugin.kt`:

- `ClockPlugin` extends `HoloNetPlugin`. It does not override `loadDependencies` because there are no external services or ViewModels — state is managed entirely inside the composable.
- `ClockModule` is the inner `@Extension` class. Its `render()` composable uses a `LaunchedEffect` coroutine that updates a `MutableState<String>` every 1000 ms using `SimpleDateFormat("HH:mm:ss")`. Rendered as a single large text (`40.sp`).

The plugin applies the `dk.holonet.plugin` Gradle plugin but does not use the `holoNetPlugin { config { } }` DSL because it has no user-configurable fields.

### When to Follow This Pattern
Use this structure (no ViewModel, state inside composable) only for self-contained plugins with no I/O. Anything involving network calls, file access, or background work should use a ViewModel and Koin injection (see `CalendarPlugin` or `RSSPlugin` for reference).
