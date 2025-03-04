import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:go_router/go_router.dart';
import 'package:secure_scan_app/core/presentation/pages/base_page.dart';
import 'package:secure_scan_app/core/shared/constants/assets_constants.dart';

// TODO: Here documentation
class HomePage extends StatelessWidget {
  const HomePage({super.key});

  @override
  Widget build(BuildContext context) {
    return BasePage(
      backgroundColor: const Color(0xFF333333),
      child: SafeArea(
        child: Padding(
          padding: const EdgeInsets.all(24),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Image.asset(AssetsConstantsImg.imgQR, width: 120, height: 120),
              const SizedBox(height: 24),
              const Center(
                child: Text(
                  'Antes de empezar',
                  style: TextStyle(
                    color: Color(0xFFFFFFFF),
                    fontSize: 36,
                    fontWeight: FontWeight.w600,
                  ),
                ),
              ),
              const SizedBox(height: 16),
              const Center(
                child: Text(
                  'Las vistas en esta app no tienen funcionalidad, solo se agregaron de forma rápida para agregar una "interfaz gráfica" más agradable a los ojos',
                  textAlign: TextAlign.center,
                  style: TextStyle(
                    color: Color(0xFFFFFFFF),
                    fontSize: 17,
                  ),
                ),
              ),
              const SizedBox(height: 16),
              GestureDetector(
                onTap: () {
                  context.go('/qr_page');
                },
                child: Image.asset(
                  AssetsConstantsImg.imgBtnNext,
                  width: 120,
                  height: 120,
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
