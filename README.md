# Cross Plugin (cmp-ide-cross)

Cross-platform IDE tooling powered by Kotlin Multiplatform and Compose Multiplatform.
One UI codebase — four targets: an IntelliJ Platform plugin, a VS Code extension,
a JVM desktop app, and a browser build compiled to Kotlin/JS.

## Architecture

Platform differences are resolved through `expect`/`actual` bridges instead of
branching logic. A single `Bridge` aggregates three sub-bridges:

- **`ResBridge`** — icons, images, and localized strings, backed by IntelliJ's
  `IconLoader` inside the plugin and by Compose Multiplatform resources
  (`compose.components.resources`) everywhere else.
- **`SysBridge`** — native notifications and confirm dialogs, delegated to the
  host platform (IntelliJ dialogs, VS Code's `showInformationMessage`, or a
  Compose `AlertDialog` fallback where no native API exists).
- **`PlatformComponents`** — UI primitives rendered through Jewel inside the
  IDE, falling back to Material3 everywhere else, behind the same call site.

Targets with no platform-specific behavior implement the shared `*Empty`
no-op defaults instead of duplicating logic per platform.

Inside `plugin-idea`, the UI tree is hosted in a Swing `ComposePanel` bridged
into an IntelliJ tool window. Inside `plugin-vscode`, the same tree is served
through a VS Code webview, with resource paths remapped via `asWebviewUri`
and a `<base href>` tag so relative Compose resource fetches resolve correctly.

## Tech stack

- **Kotlin**: 2.4.0
- **Compose Multiplatform**: 1.10.0
- **UI**: Jewel `0.34.0-253.31033.149` (IntelliJ theming), Material3 `1.9.0` (fallback)
- **Navigation**: Navigation 3 `1.1.1`
- **DI**: Koin `4.2.0`
- **Networking**: Ktor `3.1.2` (OkHttp engine on JVM, JS engine on web)
- **Serialization / time**: kotlinx.serialization, kotlinx.datetime `0.8.0`
- **Coroutines**: kotlinx.coroutines `1.11.0`
- **Logging**: Kermit `2.0.5`
- **Build**: IntelliJ Platform Gradle Plugin `2.17.0`

## Project Structure

```
plugin-ui/
├── shared/        # Common code: Compose UI, bridges (ResBridge/SysBridge/PlatformComponents), view models
├── resources/     # Shared Compose Multiplatform resources (icons, images, strings)
├── desktopApp/    # JVM desktop launcher (Compose Desktop application)
└── webApp/        # Kotlin/JS bundle, embedded into the VS Code webview
plugin-idea/       # IntelliJ Platform plugin (hosts the UI in a Swing ComposePanel)
plugin-vscode/     # VS Code extension (hosts the UI in a webview)
```

## Running the Apps

### Desktop

```bash
./gradlew :plugin-ui:desktopApp:hotRun --mainClass "com.keygenqt.ide.cross.MainKt"
./gradlew :plugin-ui:desktopApp:run
```

### Web (VS Code Webview bundle)

```bash
./gradlew :plugin-ui:webApp:jsBrowserDevelopmentRun
```

### IntelliJ Platform Plugin

```bash
./gradlew :plugin-idea:runIde
```

### VS Code Extension

```bash
./gradlew :plugin-ui:webApp:launchVscode
```
