import 'package:injectable/injectable.dart';
import 'package:secure_scan_app/core/domain/use_cases/base_use_case.dart';
import 'package:secure_scan_app/core/platform/pigeon/schema.g.dart';
import 'package:secure_scan_app/features/qr_scanner/domain/repositories/qr_repository.dart';

/// A use case for listening to new QR codes as they are detected.
///
/// [OnNewQRUseCase] implements [BaseStreamUseCase] and provides a stream
/// of QR data updates by interacting with [QrRepository].
@injectable
class OnNewQRUseCase implements BaseStreamUseCase<List<QRData>, void> {
  final QrRepository _qrRepository;

  OnNewQRUseCase(this._qrRepository);

  @override
  Stream<List<QRData>> execute(void params) {
    return _qrRepository.streamQRCodes();
  }
}
