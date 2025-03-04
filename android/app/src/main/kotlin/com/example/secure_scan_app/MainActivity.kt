package com.example.secure_scan_app

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.secure_scan_app.data.services.QRScannerHandler
import com.example.secure_scan_app.presentation.viewmodels.QRViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.flutter.embedding.android.FlutterFragmentActivity
import io.flutter.embedding.engine.FlutterEngine
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * MainActivity that integrates Flutter with native Android code.
 * Uses Hilt for dependency injection and manages QR code scanning.
 */
@AndroidEntryPoint
class MainActivity : FlutterFragmentActivity() {
    @Inject
    lateinit var qrScannerHandler: QRScannerHandler
    private lateinit var qrScannerLauncher: ActivityResultLauncher<Intent>
    private val viewModel: QRViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Register ActivityResultLauncher once in onCreate()
        qrScannerLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                qrScannerHandler.handleActivityResult(result.resultCode, result.data)
            }

        // Start collecting QR codes
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.savedQRCodes.collectLatest { qrList ->
                    qrScannerHandler.sendQRCodesToFlutter(qrList.map { it.qrText })
                }
            }
        }
    }


    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        // Initialize qrScannerHandler without requiring `context`
        qrScannerHandler.initialize(this, flutterEngine, qrScannerLauncher)
    }
}
