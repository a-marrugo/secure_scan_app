package com.example.secure_scan_app.domain.use_cases

import com.example.secure_scan_app.domain.entities.QRDataEntity
import com.example.secure_scan_app.domain.repositories.QRRepository
import com.example.secure_scan_app.domain.use_cases.GetSavedQRUseCase
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class GetSavedQRUseCaseTest {

    private lateinit var repository: QRRepository
    private lateinit var getSavedQRUseCase: GetSavedQRUseCase

    @Before
    fun setUp() {
        repository = mock(QRRepository::class.java)
        getSavedQRUseCase = GetSavedQRUseCase(repository)
    }

    @Test
    fun `invoke should return a list of saved QRDataEntity`(): Unit = runBlocking {

        val qrList =
            listOf(QRDataEntity(id = 1, qrText = "QR1"), QRDataEntity(id = 2, qrText = "QR2"))
        `when`(repository.getAllQRs()).thenReturn(flowOf(qrList))

        val result = getSavedQRUseCase().toList()

        assertEquals(
            qrList.map { it.copy(id = 0, timestamp = 0) },
            result.flatten().map { it.copy(id = 0, timestamp = 0) }
        )
        verify(repository).getAllQRs()
    }
}
