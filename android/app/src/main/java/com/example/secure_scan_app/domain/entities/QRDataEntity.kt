package com.example.secure_scan_app.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "qr_data")
data class QRDataEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val qrText: String,
    val timestamp: Long = System.currentTimeMillis()
)
