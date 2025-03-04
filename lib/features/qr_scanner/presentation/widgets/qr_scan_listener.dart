import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:secure_scan_app/features/qr_scanner/presentation/bloc/scan_qr/scan_qr_bloc.dart';
import 'package:secure_scan_app/features/qr_scanner/presentation/bloc/scan_qr/scan_qr_state.dart';
import 'package:secure_scan_app/features/qr_scanner/presentation/widgets/qr_success_dialog.dart';

/// Listens to the [ScanQRBloc] state changes and responds accordingly.
///
/// - Displays a [QRSuccessDialog] when a QR code is scanned successfully.
/// - Shows an error message via [ScaffoldMessenger] if scanning fails.
class QRScanListener extends StatelessWidget {
  final ScanQRBloc scanQRBloc;

  /// Creates a QRScanListener that listens to the provided [ScanQRBloc].
  const QRScanListener({required this.scanQRBloc, super.key});

  @override
  Widget build(BuildContext context) {
    return BlocListener<ScanQRBloc, ScanQRState>(
      listener: (context, state) {
        if (state is ScanQRSuccess) {
          showDialog(
            context: context,
            builder: (_) => QRSuccessDialog(data: state.data.value ?? ''),
          );
        } else if (state is ScanQRError) {
          ScaffoldMessenger.of(context).showSnackBar(
            SnackBar(
              content: Text("Error: ${state.message}"),
              backgroundColor: Colors.red,
            ),
          );
        }
      },
      child: const SizedBox.shrink(),
    );
  }
}
