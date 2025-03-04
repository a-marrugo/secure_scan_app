package com.example.secure_scan_app.domain.repositories

import com.example.secure_scan_app.domain.entities.QRDataEntity
import kotlinx.coroutines.flow.Flow

/**
 * Interface that defines operations for managing QR code data in the database.
 *
 * This interface follows the repository pattern and provides methods to retrieve, insert, and delete QR codes.
 */
interface QRRepository {
    /**
     * Retrieves all stored QR codes from the database.
     *
     * @return A `Flow` that emits a list of `QRDataEntity` each time the data changes.
     */
    fun getAllQRs(): Flow<List<QRDataEntity>>

    /**
     * Inserts a new QR code into the database.
     *
     * @param qrData The `QRDataEntity` representing the QR code to be stored.
     */
    suspend fun insertQR(qrData: QRDataEntity)
}
