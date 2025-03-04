import 'package:flutter/material.dart';
import 'package:secure_scan_app/core/shared/constants/assets_constants.dart';
import 'package:secure_scan_app/features/qr_scanner/presentation/bloc/qr_event.dart';
import 'package:secure_scan_app/features/qr_scanner/presentation/bloc/scan_qr/scan_qr_bloc.dart';

/// A button widget to trigger the QR scan process.
class ScanQRButton extends StatelessWidget {
  /// Bloc responsible for handling QR scan events.
  final ScanQRBloc scanQRBloc;

  const ScanQRButton({required this.scanQRBloc, super.key});

  @override
  Widget build(BuildContext context) {
    return Align(
      alignment: Alignment.bottomCenter,
      child: GestureDetector(
        onTap: () => scanQRBloc.add(ScanQRCode()),
        child: Image.asset(AssetsConstantsImg.imgScanQR, fit: BoxFit.cover),
      ),
    );
  }
}
