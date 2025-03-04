import 'package:flutter_test/flutter_test.dart';
import 'package:mocktail/mocktail.dart';
import 'package:secure_scan_app/features/qr_scanner/domain/repositories/qr_repository.dart';
import 'package:secure_scan_app/features/qr_scanner/domain/use_cases/on_new_qr_use_case.dart';
import 'package:secure_scan_app/core/platform/pigeon/schema.g.dart';

class MockQrRepository extends Mock implements QrRepository {}

void main() {
  late MockQrRepository mockQrRepository;
  late OnNewQRUseCase onNewQRUseCase;

  setUpAll(() {
    registerFallbackValue(<List<QRData>>[]);
  });

  setUp(() {
    mockQrRepository = MockQrRepository();
    onNewQRUseCase = OnNewQRUseCase(mockQrRepository);
  });

  test('Should output a list of QRData when new QRs are detected', () {
    final qrDataList = [QRData()];

    when(() => mockQrRepository.streamQRCodes())
        .thenAnswer((_) => Stream.value(qrDataList));

    final result = onNewQRUseCase.execute(null);

    expectLater(result, emits(qrDataList));
  });
}
