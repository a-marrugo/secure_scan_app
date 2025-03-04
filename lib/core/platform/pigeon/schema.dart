import 'package:pigeon/pigeon.dart';

class QRData {
  String? value;
  int? timestamp;
}

@HostApi()
abstract class QRScannerApi {
  @async
  QRData scanQRCode();
}

@FlutterApi()
abstract class QRScannerUpdates {
  void onNewQRCodes(List<QRData> qrCodes);
}

@HostApi()
abstract class BiometricAuthApi {
  bool authenticate();
}
