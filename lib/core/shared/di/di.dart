import 'package:get_it/get_it.dart';
import 'package:injectable/injectable.dart';
import 'package:secure_scan_app/core/platform/pigeon/schema.g.dart';
import 'package:secure_scan_app/core/shared/di/di.config.dart';

final GetIt getIt = GetIt.instance;

@module
abstract class RegisterModule {
  @lazySingleton
  QRScannerApi get qrScannerApi => QRScannerApi();
}

@InjectableInit()
void configureDependencies() => getIt.init();
