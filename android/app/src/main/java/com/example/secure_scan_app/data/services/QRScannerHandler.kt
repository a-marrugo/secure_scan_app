package com.example.secure_scan_app.data.services

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import com.example.app.pigeon.QRData
import com.example.app.pigeon.QRScannerApi
import com.example.app.pigeon.QRScannerUpdates
import com.example.secure_scan_app.presentation.activities.QRScannerActivity
import com.example.secure_scan_app.shared.constants.ActivityResultKeys
import io.flutter.embedding.engine.FlutterEngine
import javax.inject.Inject

/**
 * Handles QR code scanning by integrating with Flutter and launching the QRScannerActivity.
 *
 * This class is part of the **Data Layer** as it manages data flow between the QR scanner and the application logic.
 *
 * @constructor Injects an instance of QRScannerHandler.
 */
class QRScannerHandler @Inject constructor() : QRScannerApi {
    private var qrUpdates: QRScannerUpdates? = null
    private var qrScannerLauncher: ActivityResultLauncher<Intent>? = null
    private var qrCallback: ((Result<QRData>) -> Unit)? = null
    private var context: Context? = null

    /**
     * Initializes the QR scanner handler, setting up the communication with Flutter.
     *
     * @param context The application context.
     * @param flutterEngine The Flutter engine used for communication with Dart.
     * @param launcher The activity result launcher for starting the QR scanner activity.
     */
    fun initialize(
        context: Context,
        flutterEngine: FlutterEngine,
        launcher: ActivityResultLauncher<Intent>
    ) {
        this.context = context.applicationContext
        val binaryMessenger = flutterEngine.dartExecutor.binaryMessenger
        QRScannerApi.setUp(flutterEngine.dartExecutor.binaryMessenger, this)
        qrUpdates = QRScannerUpdates(binaryMessenger)
        qrScannerLauncher = launcher
    }


    /**
     * Launches the QR scanner activity and sets a callback to return the scanned QR data.
     *
     * @param callback The callback function invoked with the scan result.
     */
    override fun scanQRCode(callback: (Result<QRData>) -> Unit) {
        qrCallback = callback
        val intent = Intent(context, QRScannerActivity::class.java)
        qrScannerLauncher?.launch(intent)
    }

    /**
     * Sends the scanned QR codes to the Flutter side via the QRScannerUpdates channel.
     *
     * @param qrList A list of scanned QR codes as strings.
     */
    fun sendQRCodesToFlutter(qrList: List<String>) {
        val qrDataList = qrList.map { QRData(value = it) }
        qrUpdates?.onNewQRCodes(qrDataList) { }
    }

    /**
     * Handles the result from the QR scanner activity and invokes the callback if a QR code is detected.
     *
     * @param resultCode The result code from the activity.
     * @param data The intent containing the scanned QR result.
     */
    fun handleActivityResult(resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(ActivityResultKeys.QR_RESULT)?.let { qrText ->
                qrCallback?.invoke(Result.success(QRData(qrText)))
            }
        }
    }
}
