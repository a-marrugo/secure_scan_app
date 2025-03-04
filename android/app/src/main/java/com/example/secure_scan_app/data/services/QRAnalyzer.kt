package com.example.secure_scan_app.data.services


import android.util.Log
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.common.InputImage

/**
 * Analyzes camera frames to detect QR codes using ML Kit's BarcodeScanner.
 *
 * @property barcodeScanner The ML Kit barcode scanner used for QR code detection.
 * @property onQrDetected A callback function triggered when a QR code is detected, returning the scanned value as a String.
 */
class QRAnalyzer(
    private val barcodeScanner: BarcodeScanner,
    private val onQrDetected: (String) -> Unit
) : ImageAnalysis.Analyzer {

    /**
     * Processes each camera frame to detect QR codes.
     *
     * @param imageProxy The image frame captured by CameraX.
     */
    @OptIn(ExperimentalGetImage::class)
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image ?: return
        val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

        barcodeScanner.process(image)
            .addOnSuccessListener { barcodes ->
                barcodes.forEach { barcode ->
                    barcode.displayValue?.let { onQrDetected(it) }
                }
            }
            .addOnCompleteListener { imageProxy.close() }
            .addOnFailureListener { Log.e("QRAnalyzer", "Error in QR analysis", it) }
    }
}
