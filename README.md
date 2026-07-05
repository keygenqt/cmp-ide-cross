# CMP IDEA Cross

Cross-platform IDE tooling powered by Compose Multiplatform.  
One UI codebase — three runtimes: Desktop, VS Code Webview, IntelliJ IDEA Plugin.

## Project Structure

```
cmp-ui/           # Shared Compose Multiplatform UI
├── shared/       # Common code (VibePlatform, App UI)
├── desktopApp/   # Desktop tray application
└── webApp/       # Kotlin/JS bundle for VS Code Webview
cmp-plugin/    # IntelliJ IDEA Plugin (ComposePanel)
```

## Running the Apps

### Desktop (tray application)
```bash
./gradlew :cmp-ui:desktopApp:hotRun --auto   # Hot reload
./gradlew :cmp-ui:desktopApp:run              # Standard run
```

### Web (VS Code Webview bundle)
```bash
./gradlew :cmp-ui:webApp:jsBrowserDevelopmentRun   # JS target (Webview-compatible)
./gradlew :cmp-ui:webApp:wasmJsBrowserDevelopmentRun # Wasm target (browser only)
```

### IntelliJ IDEA Plugin
```bash
./gradlew :cmp-plugin:runIde   # Launch IDEA with plugin loaded
```

## Running Tests

- Desktop tests: `./gradlew :cmp-ui:shared:jvmTest`
- Web tests (JS): `./gradlew :cmp-ui:shared:jsTest`
- Web tests (Wasm): `./gradlew :cmp-ui:shared:wasmJsTest`
