# secure_scan_app

Prueba técnica SEEK

## Getting Started

### Requisitos Previos

Antes de ejecutar la aplicación, asegúrate de tener instalado lo siguiente:

- [Flutter](https://flutter.dev/docs/get-started/install) (Versión recomendada: 3.22.3)
- [Dart](https://dart.dev/get-dart) (Tools • Dart 3.4.4 • DevTools 2.34.3)
- [Android Studio](https://developer.android.com/studio) o [Xcode](https://developer.apple.com/xcode/) (para desarrollo nativo en Android e iOS)
- Dispositivo físico o emulador configurado

### Instalación

1. Clona este repositorio:

   ```sh
   git clone git@github.com:a-marrugo/secure_scan_app.git
   cd secure_scan_app
   ```

2. Instala las dependencias:

   ```sh
   flutter pub get
   ```

3. Si tu proyecto usa módulos nativos, corre:

   ```sh
   flutter clean
   flutter pub get
   ```

### Configuración para Android

1. Asegúrate de que el SDK de Android esté configurado en tu entorno.
2. Concede permisos si es necesario (ubicación, cámara, etc.) en `AndroidManifest.xml`.
3. Conéctate a un dispositivo o inicia un emulador:

   ```sh
   flutter devices
   ```

4. Ejecuta la app:

   ```sh
   flutter run
   ```

### Configuración para iOS

1. Instala las dependencias de CocoaPods:

   ```sh
   cd ios
   pod install
   cd ..
   ```

2. Abre `ios/Runner.xcworkspace` en Xcode y verifica la configuración del equipo (Team ID).
3. Ejecuta la aplicación en un simulador o dispositivo físico:

   ```sh
   flutter run
   ```

### Ejecución en Modo Producción

Para compilar la aplicación en modo release:

- Android:
  ```sh
  flutter build apk --release
  ```

### Solución de Problemas

- **Dependencias nativas no detectadas:**
  ```sh
  flutter clean
  flutter pub get
  ```
- **Errores en Android:** Verifica que el SDK y NDK de Android estén configurados correctamente en `local.properties`.

### Licencia

Este proyecto está bajo la licencia [MIT](LICENSE).
