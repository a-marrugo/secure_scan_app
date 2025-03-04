import 'package:injectable/injectable.dart';
import 'package:secure_scan_app/core/domain/use_cases/base_use_case.dart';
import 'package:secure_scan_app/core/platform/pigeon/schema.g.dart';
import 'package:secure_scan_app/features/qr_scanner/domain/repositories/qr_repository.dart';

/// A use case for scanning and saving a QR code.
///
/// [SaveQrUseCase] implements [BaseUseCase] and interacts with [QrRepository]
/// to trigger a QR scan and return the scanned data.
@injectable
class SaveQrUseCase implements BaseUseCase<QRData, void> {
  final QrRepository _qrRepository;

  SaveQrUseCase(this._qrRepository);

  @override
  Future<QRData> execute(void params) => _qrRepository.scanQR();
}
