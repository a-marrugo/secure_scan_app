import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:injectable/injectable.dart';
import 'package:secure_scan_app/core/platform/pigeon/schema.g.dart';
import 'package:secure_scan_app/features/qr_scanner/domain/use_cases/on_new_qr_use_case.dart';

@injectable
class OnNewQRBloc extends Cubit<List<QRData>> {
  final OnNewQRUseCase _onNewQRUseCase;

  OnNewQRBloc(this._onNewQRUseCase) : super([]) {
    _subscribeToQRUpdates();
  }

  void _subscribeToQRUpdates() {
    _onNewQRUseCase.execute(null).listen(
          (qrCodes) => emit(qrCodes),
          onError: (error) => emit(
              []), // En caso de error, emite una lista vacía o maneja el error
        );
  }
}
