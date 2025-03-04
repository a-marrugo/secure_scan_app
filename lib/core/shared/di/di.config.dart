// GENERATED CODE - DO NOT MODIFY BY HAND

// **************************************************************************
// InjectableConfigGenerator
// **************************************************************************

// ignore_for_file: type=lint
// coverage:ignore-file

// ignore_for_file: no_leading_underscores_for_library_prefixes
import 'package:get_it/get_it.dart' as _i174;
import 'package:injectable/injectable.dart' as _i526;
import 'package:secure_scan_app/core/platform/pigeon/schema.g.dart' as _i255;
import 'package:secure_scan_app/core/shared/di/di.dart' as _i150;
import 'package:secure_scan_app/features/qr_scanner/data/repositories/qr_repository_impl.dart'
    as _i947;
import 'package:secure_scan_app/features/qr_scanner/domain/repositories/qr_repository.dart'
    as _i453;
import 'package:secure_scan_app/features/qr_scanner/domain/use_cases/on_new_qr_use_case.dart'
    as _i272;
import 'package:secure_scan_app/features/qr_scanner/domain/use_cases/save_qr_use_case.dart'
    as _i685;
import 'package:secure_scan_app/features/qr_scanner/presentation/bloc/on_new_qrs/on_new_qr_bloc.dart'
    as _i883;
import 'package:secure_scan_app/features/qr_scanner/presentation/bloc/scan_qr/scan_qr_bloc.dart'
    as _i1049;

extension GetItInjectableX on _i174.GetIt {
// initializes the registration of main-scope dependencies inside of GetIt
  _i174.GetIt init({
    String? environment,
    _i526.EnvironmentFilter? environmentFilter,
  }) {
    final gh = _i526.GetItHelper(
      this,
      environment,
      environmentFilter,
    );
    final registerModule = _$RegisterModule();
    gh.lazySingleton<_i255.QRScannerApi>(() => registerModule.qrScannerApi);
    gh.factory<_i453.QrRepository>(
        () => _i947.QrRepositoryImpl(gh<_i255.QRScannerApi>()));
    gh.factory<_i685.SaveQrUseCase>(
        () => _i685.SaveQrUseCase(gh<_i453.QrRepository>()));
    gh.factory<_i272.OnNewQRUseCase>(
        () => _i272.OnNewQRUseCase(gh<_i453.QrRepository>()));
    gh.factory<_i883.OnNewQRBloc>(
        () => _i883.OnNewQRBloc(gh<_i272.OnNewQRUseCase>()));
    gh.factory<_i1049.ScanQRBloc>(
        () => _i1049.ScanQRBloc(gh<_i685.SaveQrUseCase>()));
    return this;
  }
}

class _$RegisterModule extends _i150.RegisterModule {}
