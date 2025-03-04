import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:secure_scan_app/core/platform/pigeon/schema.g.dart';
import 'package:secure_scan_app/features/qr_scanner/presentation/bloc/on_new_qrs/on_new_qr_bloc.dart';
import 'package:secure_scan_app/features/qr_scanner/presentation/widgets/qr_card.dart';

/// A widget that displays a list of scanned QR codes using [BlocBuilder].
///
/// - Listens to [OnNewQRBloc] and updates when new QR codes are added.
/// - Shows a message if no QR codes are available.
/// - Displays each QR code using [QRCard].
class QRListView extends StatelessWidget {
  const QRListView({super.key});

  @override
  Widget build(BuildContext context) {
    return BlocBuilder<OnNewQRBloc, List<QRData>>(
      // Listens to QR data updates
      builder: (context, qrCodes) {
        if (qrCodes.isEmpty) {
          return const Center(
            child: Padding(
              padding: EdgeInsets.only(bottom: 150),
              child: Text(
                'No QR codes found',
                style: TextStyle(color: Color(0xFFD9D9D9), fontSize: 18),
              ),
            ),
          );
        }
        return ListView.builder(
          padding: const EdgeInsets.only(bottom: 150),
          itemCount: qrCodes.length,
          itemBuilder: (context, index) => QRCard(qrCode: qrCodes[index]),
        );
      },
    );
  }
}
