package com.example.secure_scan_app.domain.use_cases

import com.example.secure_scan_app.domain.entities.QRDataEntity
import com.example.secure_scan_app.domain.repositories.QRRepository
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.kotlin.anyOrNull
import org.mockito.kotlin.check
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class SaveQRUseCaseTest {

    private lateinit var repository: QRRepository
    private lateinit var saveQRUseCase: SaveQRUseCase

    @Before
    fun setUp() {
        repository = mock()
        saveQRUseCase = SaveQRUseCase(repository)
    }

    @Test
    fun `should save QR code when it does not exist`() = runBlocking {
        val qrText = "new_qr_code"
        whenever(repository.getAllQRs()).thenReturn(flowOf(emptyList()))

        val result = saveQRUseCase(qrText)

        assertTrue(result)
        verify(repository).insertQR(check { assertNotNull(it.qrText) })

    }


    @Test
    fun `should not save QR code when it already exists`() = runBlocking {
        val qrText = "existing_qr_code"
        whenever(repository.getAllQRs()).thenReturn(flowOf(listOf(QRDataEntity(qrText = qrText))))

        val result = saveQRUseCase(qrText)

        assertFalse(result)
        verify(repository, never()).insertQR(anyOrNull())
    }
}
