import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:injectable/injectable.dart';
import 'package:secure_scan_app/features/qr_scanner/domain/use_cases/save_qr_use_case.dart';
import 'package:secure_scan_app/features/qr_scanner/presentation/bloc/qr_event.dart';
import 'package:secure_scan_app/features/qr_scanner/presentation/bloc/scan_qr/scan_qr_state.dart';

/// [ScanQRBloc] handles QR scanning events and manages the scanning state.
/// It listens for [ScanQRCode] events, triggers the scanning process,
/// and updates the UI state accordingly.
@injectable
class ScanQRBloc extends Bloc<QREvent, ScanQRState> {
  final SaveQrUseCase _saveQrUseCase;

  ScanQRBloc(this._saveQrUseCase) : super(ScanQRInitial()) {
    on<ScanQRCode>((event, emit) async {
      emit(ScanQRLoading());
      try {
        final data = await _saveQrUseCase.execute(null);
        emit(ScanQRSuccess(data));
      } catch (e) {
        emit(const ScanQRError("Error loading data"));
      }
    });
  }
}
