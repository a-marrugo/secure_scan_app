import 'package:equatable/equatable.dart';
import 'package:secure_scan_app/core/platform/pigeon/schema.g.dart';

abstract class OnNewQRState extends Equatable {
  const OnNewQRState();
  @override
  List<Object?> get props => [];
}

class OnNewQRInitial extends OnNewQRState {}

class OnNewQRLoading extends OnNewQRState {}

class OnNewQRSuccess extends OnNewQRState {
  final List<QRData> data;
  const OnNewQRSuccess(this.data);
  @override
  List<Object?> get props => [data];
}

class OnNewQRError extends OnNewQRState {
  final String message;
  const OnNewQRError(this.message);
  @override
  List<Object?> get props => [message];
}
