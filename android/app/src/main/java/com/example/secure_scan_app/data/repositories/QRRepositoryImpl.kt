package com.example.secure_scan_app.data.repositories

import com.example.secure_scan_app.data.dto.QRDao
import com.example.secure_scan_app.domain.entities.QRDataEntity
import com.example.secure_scan_app.domain.repositories.QRRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QRRepositoryImpl @Inject constructor(
    private val qrDao: QRDao
) : QRRepository {

    override fun getAllQRs(): Flow<List<QRDataEntity>> = qrDao.getAllQRs()

    override suspend fun insertQR(qrData: QRDataEntity) {
        qrDao.insertQR(qrData)
    }

}