# SEEK Technical Test - Flutter QR Scanner

## APK

Descarga [APK](https://upload.app/download/secure-scan-app/com.example.secure_scan_app/62b3d83a42a9217c550711792223180c79698066de6ec39198679e0e5e214e89).

## Acerca del proyecto

Este proyecto en Flutter implementa un sistema de lectura de códigos QR con almacenamiento de datos en una base de datos nativa utilizando **Room Database**. La comunicación entre los módulos nativos y Flutter se realiza de manera reactiva mediante **Pigeon**.

## Arquitectura

Se ha aplicado **arquitectura limpia**, dividiendo tanto el módulo nativo como los componentes de Flutter en capas bien definidas, donde cada capa tiene una responsabilidad específica. Esto facilita:

- **Pruebas unitarias y de integración**
- **Escalabilidad**
- **Mantenimiento del código**

## Características

- 📷 **Escaneo de códigos QR**
- 💾 **Almacenamiento local de datos** mediante **Room Database**
- 🔄 **Comunicación entre Flutter y módulos nativos** usando **Pigeon**
- ⚡ **Flujo de datos reactivo** para actualizar la interfaz en tiempo real

## Tecnologías utilizadas

- **Flutter (Dart)**
- **Pigeon** (Interfaz de comunicación entre Flutter y nativo)
- **Kotlin / Swift** (Implementación nativa)
- **Room Database** (Gestión de almacenamiento en Android)

## Getting Started

### Requisitos Previos

Antes de ejecutar la aplicación, asegúrate de tener instalado lo siguiente:

- [Flutter](https://flutter.dev/docs/get-started/install) (Versión recomendada: 3.22.3)
- [Dart](https://dart.dev/get-dart) (Tools • Dart 3.4.4 • DevTools 2.34.3)
- Dispositivo físico o emulador configurado
- `gradle.properties` Tener en el virtual machine las versiones openjdk 17 y 11

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
- \*\*Pruebas unitarias coverage

- ![image](https://github.com/user-attachments/assets/de5249e2-086a-4e19-8519-650ff6286fe7)
- <img width="245" alt="Screenshot 2025-03-04 at 12 50 30 PM" src="https://github.com/user-attachments/assets/e90fa8d0-aeae-42fb-bdcf-88cd94d2a0b3" />
