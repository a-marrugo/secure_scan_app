/// Base class for all QR-related events.
abstract class QREvent {}

/// Event triggered to initiate a QR code scan.
class ScanQRCode extends QREvent {}

/// Event triggered when new QR codes are detected.
class OnNewQRCodes extends QREvent {}
