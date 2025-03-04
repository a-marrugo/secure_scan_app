import 'dart:async';

import 'package:flutter/services.dart';
import 'package:injectable/injectable.dart';
import 'package:secure_scan_app/core/platform/pigeon/schema.g.dart';

import 'package:secure_scan_app/features/qr_scanner/domain/repositories/qr_repository.dart';

/// Implementation of [QrRepository] that interacts with the QR scanning API.
@Injectable(as: QrRepository)
class QrRepositoryImpl implements QrRepository, QRScannerUpdates {
  final QRScannerApi _api;
  final StreamController<List<QRData>> _qrStreamController =
      StreamController.broadcast();

  QrRepositoryImpl(this._api) {
    QRScannerUpdates.setUp(this);
  }

  Stream<List<QRData>> get qrStream => _qrStreamController.stream;

  @override
  Future<QRData> scanQR() async {
    try {
      final result = await _api.scanQRCode();
      return result;
    } on PlatformException catch (e) {
      throw Exception("Error scanning QR: ${e.message}");
    }
  }

  @override
  void onNewQRCodes(List<QRData> qrCodes) {
    _qrStreamController.add(qrCodes);
  }

  @override
  Stream<List<QRData>> streamQRCodes() {
    return qrStream;
  }
}
