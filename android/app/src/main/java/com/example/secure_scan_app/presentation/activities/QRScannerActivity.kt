package com.example.secure_scan_app.presentation.activities

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.Size
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.camera.core.*
import androidx.camera.core.resolutionselector.ResolutionSelector
import androidx.camera.core.resolutionselector.ResolutionStrategy
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.secure_scan_app.databinding.ActivityQrscannerBinding
import com.example.secure_scan_app.data.services.QRAnalyzer
import com.example.secure_scan_app.presentation.viewmodels.QRViewModel
import com.example.secure_scan_app.shared.constants.ActivityResultKeys
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScanning
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Activity responsible for scanning QR codes using CameraX and ML Kit.
 * Uses Hilt for dependency injection.
 */
@AndroidEntryPoint
class QRScannerActivity : ComponentActivity() {
    private lateinit var binding: ActivityQrscannerBinding
    private lateinit var cameraExecutor: ExecutorService
    private lateinit var barcodeScanner: BarcodeScanner
    private var cameraProvider: ProcessCameraProvider? = null

    private val viewModel: QRViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityQrscannerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupFullScreenMode()
        setupCamera()
        observeViewModel()

        binding.btnClose.setOnClickListener { finish() }
        startCamera()
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraProvider?.unbindAll()
        cameraExecutor.shutdown()
    }

    /**
     * Enables full-screen mode by hiding system UI elements.
     */
    private fun setupFullScreenMode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.decorView.windowInsetsController?.apply {
                hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                            View.SYSTEM_UI_FLAG_FULLSCREEN or
                            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    )
        }
    }

    /**
     * Initializes the camera executor and barcode scanner.
     */
    private fun setupCamera() {
        cameraExecutor = Executors.newSingleThreadExecutor()
        barcodeScanner = BarcodeScanning.getClient()
    }

    /**
     * Observes ViewModel's insertResult to handle scanned QR codes.
     */
    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.insertResult.collect { result ->
                result?.let { (isSaved, qrText) ->
                    if (isSaved) Log.d("QR_STORAGE", "Guardado: $qrText")

                    val intent = Intent().putExtra(ActivityResultKeys.QR_RESULT, qrText)
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                }
            }
        }
    }

    /**
     * Starts the camera and sets up image analysis for QR code scanning.
     */
    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener({
            try {
                cameraProvider = cameraProviderFuture.get()

                val preview = Preview.Builder()
                    .setResolutionSelector(
                        ResolutionSelector.Builder().setResolutionStrategy(
                            ResolutionStrategy(
                                Size(1280, 720),
                                ResolutionStrategy.FALLBACK_RULE_NONE
                            )
                        ).build()
                    )
                    .build()
                    .also { it.surfaceProvider = binding.previewView.surfaceProvider }

                val imageAnalyzer = ImageAnalysis.Builder()
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .build()
                    .also {
                        it.setAnalyzer(
                            cameraExecutor,
                            QRAnalyzer(barcodeScanner, viewModel::insertQR)
                        )
                    }

                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
                cameraProvider?.bindToLifecycle(this, cameraSelector, preview, imageAnalyzer)
            } catch (e: Exception) {
                Log.e("CameraX", "Error al iniciar la cámara: ${e.message}")
            }
        }, ContextCompat.getMainExecutor(this))
    }
}
