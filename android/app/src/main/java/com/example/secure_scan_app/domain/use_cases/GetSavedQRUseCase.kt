package com.example.secure_scan_app.domain.use_cases

import com.example.secure_scan_app.domain.entities.QRDataEntity
import com.example.secure_scan_app.domain.repositories.QRRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for retrieving all saved QR codes.
 *
 * @property repository Repository responsible for handling QR code data.
 * @constructor Creates an instance of the use case with the injected repository.
 */
class GetSavedQRUseCase @Inject constructor(
    private val repository: QRRepository
) {
    /**
     * Invokes the use case to fetch a list of saved QR codes.
     *
     * @return A flow (`Flow`) that emits a list of [QRDataEntity].
     */
    operator fun invoke(): Flow<List<QRDataEntity>> = repository.getAllQRs()
}

