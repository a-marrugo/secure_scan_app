package com.example.secure_scan_app.data.dto

import androidx.room.*
import com.example.secure_scan_app.domain.entities.QRDataEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for managing QR data in the Room database.
 *
 * This interface provides methods to insert, retrieve, and clear QR data.
 * It leverages Kotlin coroutines for asynchronous operations and Flow for
 * reactive data handling.
 */
@Dao
interface QRDao {
    /**
     * Inserts a new QR code into the database. If a conflict occurs (e.g., duplicate ID),
     * it replaces the existing entry.
     *
     * @param qrDataEntity The QR data entity to insert.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQR(qrDataEntity: QRDataEntity)

    /**
     * Retrieves all stored QR codes, ordered by ID in descending order.
     *
     * @return A Flow emitting a list of [QRDataEntity] objects whenever the data changes.
     */
    @Query("SELECT * FROM qr_data ORDER BY id DESC")
    fun getAllQRs(): Flow<List<QRDataEntity>>
}
