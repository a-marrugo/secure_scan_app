package com.example.secure_scan_app.domain.use_cases

import com.example.secure_scan_app.domain.entities.QRDataEntity
import com.example.secure_scan_app.domain.repositories.QRRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

/**
 * Use case for saving a QR code in the database.
 *
 * This class ensures that a QR code is stored only if it does not already exist.
 *
 * @property repository The repository that handles QR code persistence.
 */
class SaveQRUseCase @Inject constructor(
    private val repository: QRRepository
) {
    /**
     * Saves a QR code if it is not already in the database.
     *
     * @param qrText The text content of the QR code.
     * @return `true` if the QR code was saved, `false` if it already exists.
     */
    suspend operator fun invoke(qrText: String): Boolean {
        val existingQRs = repository.getAllQRs().first()
        if (existingQRs.none { it.qrText == qrText }) {
            repository.insertQR(QRDataEntity(qrText = qrText))
            return true
        }
        return false
    }
}
