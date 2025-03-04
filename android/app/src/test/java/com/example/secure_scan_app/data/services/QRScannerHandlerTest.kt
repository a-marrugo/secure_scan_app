package com.example.secure_scan_app.data.services

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import com.example.app.pigeon.QRScannerUpdates
import com.example.secure_scan_app.shared.constants.ActivityResultKeys
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.BinaryMessenger
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.argumentCaptor

@RunWith(MockitoJUnitRunner::class)
class QRScannerHandlerTest {

    @Mock
    private lateinit var mockLauncher: ActivityResultLauncher<Intent>

    private lateinit var qrScannerHandler: QRScannerHandler

    private val mockQrUpdates: QRScannerUpdates = mock()

    @Mock
    private lateinit var flutterEngine: FlutterEngine

    @Mock
    private lateinit var dartExecutor: DartExecutor

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        mockLauncher =mock(ActivityResultLauncher::class.java) as ActivityResultLauncher<Intent>
        `when`(flutterEngine.dartExecutor).thenReturn(dartExecutor)
        `when`(dartExecutor.binaryMessenger).thenReturn(mock(BinaryMessenger::class.java))

        qrScannerHandler = QRScannerHandler()
        qrScannerHandler.javaClass.getDeclaredField("qrUpdates").apply {
            isAccessible = true
            set(qrScannerHandler, mockQrUpdates)
        }
        qrScannerHandler.initialize(mock(Context::class.java), flutterEngine, mockLauncher)
    }



    @Test
    fun `scanQRCode should launch QRScannerActivity`() {
        qrScannerHandler.scanQRCode {}

        val intentCaptor = argumentCaptor<Intent>()
        verify(mockLauncher).launch(intentCaptor.capture())

    }

    @Test
    fun `handleActivityResult should invoke callback with QRData when result is OK`() {
        val mockIntent = mock(Intent::class.java)
        `when`(mockIntent.getStringExtra(ActivityResultKeys.QR_RESULT)).thenReturn("mock_qr_code")

        qrScannerHandler.scanQRCode { result ->
            assert(result.isSuccess)
            assert(result.getOrNull()?.value == "mock_qr_code")
        }
        qrScannerHandler.handleActivityResult(Activity.RESULT_OK, mockIntent)
    }
}
