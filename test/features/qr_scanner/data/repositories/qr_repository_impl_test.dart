import 'package:flutter_test/flutter_test.dart';
import 'package:mocktail/mocktail.dart';
import 'package:secure_scan_app/core/platform/pigeon/schema.g.dart';
import 'package:secure_scan_app/features/qr_scanner/data/repositories/qr_repository_impl.dart';

class MockQRScannerApi extends Mock implements QRScannerApi {}

void main() {
  TestWidgetsFlutterBinding.ensureInitialized();
  late MockQRScannerApi mockQRScannerApi;
  late QrRepositoryImpl qrRepository;
  final qrData = QRData();

  setUp(() {
    mockQRScannerApi = MockQRScannerApi();
    qrRepository = QrRepositoryImpl(mockQRScannerApi);
  });

  test('Should return QRData when a QR code is scanned', () async {
    when(() => mockQRScannerApi.scanQRCode()).thenAnswer((_) async => qrData);

    final result = await qrRepository.scanQR();

    expect(result, qrData);
  });
}
