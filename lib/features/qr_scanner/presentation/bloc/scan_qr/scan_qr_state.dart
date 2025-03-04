import 'package:equatable/equatable.dart';
import 'package:secure_scan_app/core/platform/pigeon/schema.g.dart';

/// Defines the states for the QR scanning process.
///
/// [ScanQRState] is an abstract base class that extends [Equatable]
/// to support state comparison and ensure efficient UI updates.
///
/// - [ScanQRInitial]: Represents the initial state before scanning starts.
/// - [ScanQRLoading]: Indicates that the scanning process is in progress.
/// - [ScanQRSuccess]: Contains the successfully scanned QR data.
/// - [ScanQRError]: Holds an error message when the scanning fails.
abstract class ScanQRState extends Equatable {
  const ScanQRState();
  @override
  List<Object?> get props => [];
}

/// Initial state before any QR scan is triggered.
class ScanQRInitial extends ScanQRState {}

/// State representing an ongoing QR scan.
class ScanQRLoading extends ScanQRState {}

/// State emitted when a QR scan is successful.
class ScanQRSuccess extends ScanQRState {
  final QRData data;
  const ScanQRSuccess(this.data);

  @override
  List<Object?> get props => [data];
}

/// State emitted when a QR scan fails.
class ScanQRError extends ScanQRState {
  final String message;
  const ScanQRError(this.message);

  @override
  List<Object?> get props => [message];
}
