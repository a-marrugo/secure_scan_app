import 'package:flutter/material.dart';
import 'package:secure_scan_app/core/shared/constants/assets_constants.dart';

/// A dialog widget to display QR scan success information.
class QRSuccessDialog extends StatelessWidget {
  /// Scanned QR data to be displayed.
  final String data;

  const QRSuccessDialog({required this.data, super.key});

  @override
  Widget build(BuildContext context) {
    return Dialog(
      backgroundColor: Colors.transparent,
      child: Card(
        color: Colors.grey[900],
        elevation: 8,
        shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(6)),
        child: Padding(
          padding: const EdgeInsets.fromLTRB(24, 19, 24, 12),
          child: Column(
            mainAxisSize: MainAxisSize.min,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Row(
                children: [
                  Image.asset(AssetsConstantsImg.imgQR, width: 49, height: 49),
                  const SizedBox(width: 16),
                  const Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(
                        'Data',
                        style:
                            TextStyle(color: Color(0xFFD9D9D9), fontSize: 18),
                      ),
                      Text(
                        '16 Dec 2022, 9:30 pm',
                        style:
                            TextStyle(color: Color(0xFFA4A4A4), fontSize: 13),
                      ),
                    ],
                  ),
                ],
              ),
              const Divider(
                  color: Color(0xFF858585), thickness: 0.3, height: 32),
              Text(
                data,
                style: const TextStyle(color: Color(0xFFD9D9D9), fontSize: 17),
              ),
              Center(
                child: TextButton(
                  onPressed: () => Navigator.pop(context),
                  child: const Text(
                    'Close QR Code',
                    style: TextStyle(color: Color(0xFFFDB623), fontSize: 15),
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
