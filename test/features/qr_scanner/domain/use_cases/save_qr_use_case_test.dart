import 'package:flutter_test/flutter_test.dart';
import 'package:mocktail/mocktail.dart';
import 'package:secure_scan_app/core/platform/pigeon/schema.g.dart';
import 'package:secure_scan_app/features/qr_scanner/domain/repositories/qr_repository.dart';
import 'package:secure_scan_app/features/qr_scanner/domain/use_cases/save_qr_use_case.dart';

// Mock class for QrRepository
class MockQrRepository extends Mock implements QrRepository {}

void main() {
  late SaveQrUseCase saveQrUseCase;
  late MockQrRepository mockQrRepository;

  final qrData = QRData(value: 'Test QR');

  setUp(() {
    mockQrRepository = MockQrRepository();
    saveQrUseCase = SaveQrUseCase(mockQrRepository);

    registerFallbackValue(qrData);
  });

  test('should return QRData when scanQR is successful', () async {
    // Arrange

    when(() => mockQrRepository.scanQR())
        .thenAnswer((_) => Future.value(qrData));

    // Act
    final result = await saveQrUseCase.execute(null);

    // Assert
    expect(result, equals(qrData));
    verify(() => mockQrRepository.scanQR()).called(1);
    verifyNoMoreInteractions(mockQrRepository);
  });

  test('should throw an exception when scanQR fails', () async {
    // Arrange
    when(() => mockQrRepository.scanQR()).thenThrow(Exception('Scan failed'));

    // Act & Assert
    expect(() => saveQrUseCase.execute(null), throwsException);
    verify(() => mockQrRepository.scanQR()).called(1);
    verifyNoMoreInteractions(mockQrRepository);
  });
}
