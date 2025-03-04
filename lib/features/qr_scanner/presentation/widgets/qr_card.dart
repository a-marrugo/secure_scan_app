import 'package:flutter/material.dart';
import 'package:secure_scan_app/core/platform/pigeon/schema.g.dart';
import 'package:secure_scan_app/core/shared/constants/assets_constants.dart';

/// A card widget displaying QR code data.
class QRCard extends StatelessWidget {
  /// The QR data to be displayed.
  final QRData qrCode;

  const QRCard({required this.qrCode, super.key});

  @override
  Widget build(BuildContext context) {
    return Card(
      color: const Color(0xFF333333),
      elevation: 8,
      shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(6)),
      child: Padding(
        padding: const EdgeInsets.symmetric(vertical: 13, horizontal: 15),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            Expanded(
              child: Row(
                children: [
                  Image.asset(AssetsConstantsImg.imgQR, width: 33, height: 33),
                  const SizedBox(width: 15),
                  Expanded(
                    child: Column(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          qrCode.value ?? '',
                          maxLines: 1,
                          overflow: TextOverflow.ellipsis,
                          style: const TextStyle(
                              color: Color(0xFFD9D9D9), fontSize: 15),
                        ),
                        const Text(
                          'Data',
                          style: TextStyle(
                            color: Color(0xFFA4A4A4),
                            fontSize: 11,
                          ),
                        ),
                      ],
                    ),
                  ),
                ],
              ),
            ),
            Column(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              crossAxisAlignment: CrossAxisAlignment.end,
              children: [
                Image.asset(
                  width: 24,
                  AssetsConstantsIc.icDelete,
                  fit: BoxFit.cover,
                ),
                const Text(
                  '16 Dec 2022, 9:30 pm',
                  style: TextStyle(
                    color: Color(0xFFA4A4A4),
                    fontSize: 11,
                  ),
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }
}
