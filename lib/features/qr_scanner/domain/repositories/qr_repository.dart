import 'package:secure_scan_app/core/platform/pigeon/schema.g.dart';

/// Abstract repository for handling QR code operations.
///
/// [QrRepository] defines the contract for scanning and streaming QR codes.
/// Implementations of this repository should provide concrete logic for these operations.
abstract class QrRepository {
  /// Scans a QR code and returns the scanned data as a [QRData] object.
  Future<QRData> scanQR();

  /// Provides a stream of detected QR codes as a list of [QRData].
  Stream<List<QRData>> streamQRCodes();
}
