import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:secure_scan_app/core/shared/constants/assets_constants.dart';
import 'package:secure_scan_app/core/shared/di/di.dart';
import 'package:secure_scan_app/features/qr_scanner/presentation/bloc/on_new_qrs/on_new_qr_bloc.dart';
import 'package:secure_scan_app/features/qr_scanner/presentation/bloc/scan_qr/scan_qr_bloc.dart';
import 'package:secure_scan_app/core/presentation/pages/base_page.dart';
import 'package:secure_scan_app/features/qr_scanner/presentation/widgets/qr_list_view.dart';
import 'package:secure_scan_app/features/qr_scanner/presentation/widgets/qr_scan_listener.dart';
import 'package:secure_scan_app/features/qr_scanner/presentation/widgets/scan_qr_button.dart';

/// QRPage is the main page for scanning and displaying QR codes.
/// It initializes the necessary BLoCs and provides them to the widget tree.
/// The page consists of:
/// - A QR scan listener to handle scan events.
/// - A list view to display detected QR codes.
/// - A floating button to initiate QR scanning.
class QRPage extends StatefulWidget {
  const QRPage({super.key});

  @override
  State<QRPage> createState() => _QRPageState();
}

class _QRPageState extends State<QRPage> {
  late ScanQRBloc scanQRBloc;
  late OnNewQRBloc onNewQRBloc;

  @override
  void initState() {
    super.initState();
    scanQRBloc = getIt<ScanQRBloc>();
    onNewQRBloc = getIt<OnNewQRBloc>();
  }

  @override
  Widget build(BuildContext context) {
    return MultiBlocProvider(
      providers: [
        BlocProvider.value(value: scanQRBloc),
        BlocProvider.value(value: onNewQRBloc),
      ],
      child: BasePage(
        pathBackground: AssetsConstantsBg.bgDisplayQR,
        child: SafeArea(
          child: Stack(
            children: [
              Padding(
                padding: const EdgeInsets.symmetric(horizontal: 20),
                child: Column(
                  children: [
                    QRScanListener(scanQRBloc: scanQRBloc),
                    const SizedBox(height: kToolbarHeight * 3),
                    const Expanded(child: QRListView()),
                  ],
                ),
              ),
              ScanQRButton(scanQRBloc: scanQRBloc),
            ],
          ),
        ),
      ),
    );
  }
}
