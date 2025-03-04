package com.example.secure_scan_app.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.secure_scan_app.domain.entities.QRDataEntity
import com.example.secure_scan_app.domain.use_cases.GetSavedQRUseCase
import com.example.secure_scan_app.domain.use_cases.SaveQRUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * ViewModel responsible for managing QR code operations.
 * Uses Hilt for dependency injection.
 */
@HiltViewModel
class QRViewModel @Inject constructor(
    private val saveQRUseCase: SaveQRUseCase,
    getSavedQRUseCase: GetSavedQRUseCase,
) : ViewModel() {

    /**
     * Holds the result of a QR code insertion.
     * Pair contains success status and the inserted QR text.
     */
    private val _insertResult = MutableStateFlow<Pair<Boolean, String>?>(null)
    val insertResult: StateFlow<Pair<Boolean, String>?> = _insertResult

    /**
     * StateFlow containing the list of saved QR codes.
     */
    val savedQRCodes: StateFlow<List<QRDataEntity>> = getSavedQRUseCase()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    /**
     * Inserts a QR code into the database.
     * Runs the operation on the IO dispatcher.
     */
    fun insertQR(qrText: String) {
        viewModelScope.launch {
            val isSaved = withContext(Dispatchers.IO) { saveQRUseCase(qrText) }
            _insertResult.value = Pair(isSaved, qrText)
        }
    }

}

