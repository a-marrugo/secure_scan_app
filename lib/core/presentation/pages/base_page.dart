import 'package:flutter/material.dart';
import 'package:secure_scan_app/core/shared/constants/assets_constants.dart';

class BasePage extends StatelessWidget {
  const BasePage({
    super.key,
    required this.child,
    this.appBarTitle,
    this.appBarActions,
    this.bottomNavigationBar,
    this.pathBackground,
    this.backgroundColor,
  });

  /// Child to render inside base page
  final Widget child;

  /// Title for the AppBar
  final String? appBarTitle;

  /// Actions for the AppBar
  final List<Widget>? appBarActions;

  /// Bottom navigation bar
  final Widget? bottomNavigationBar;

  final String? pathBackground;

  final Color? backgroundColor;

  @override
  Widget build(BuildContext context) {
    return Stack(
      children: [
        if (pathBackground != null)
          Positioned.fill(
            child: Image.asset(
              pathBackground ?? '',
              fit: BoxFit.cover,
            ),
          ),
        Scaffold(
          backgroundColor: backgroundColor ?? Colors.transparent,
          appBar: appBarTitle != null
              ? AppBar(
                  title: Text(appBarTitle!),
                  actions: appBarActions,
                  backgroundColor: Colors.white,
                  elevation: 0,
                )
              : null,
          body: child,
          bottomNavigationBar: bottomNavigationBar,
        ),
      ],
    );
  }
}
