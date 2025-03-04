package com.example.secure_scan_app.data.repositories

import com.example.secure_scan_app.data.dto.QRDao
import com.example.secure_scan_app.domain.entities.QRDataEntity
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class QRRepositoryImplTest {

    private lateinit var qrRepository: QRRepositoryImpl
    private lateinit var qrDao: QRDao

    @Before
    fun setUp() {
        qrDao = mock(QRDao::class.java)
        qrRepository = QRRepositoryImpl(qrDao)
    }

    @Test
    fun `getAllQRs should return flow of QRDataEntity list`() = runTest {
        val expectedList =
            listOf(QRDataEntity(id = 1, qrText = "QR1"), QRDataEntity(id = 2, qrText = "QR2"))
        whenever(qrDao.getAllQRs()).thenReturn(flowOf(expectedList))

        val result = qrRepository.getAllQRs()

        result.collect { list ->
            assert(list == expectedList)
        }

        verify(qrDao, times(1)).getAllQRs()
    }

    @Test
    fun `insertQR should call insertQR on QRDao`() = runTest {
        val qrData = QRDataEntity(id = 1, qrText = "QR1")

        qrRepository.insertQR(qrData)

        verify(qrDao, times(1)).insertQR(qrData)
    }

}
