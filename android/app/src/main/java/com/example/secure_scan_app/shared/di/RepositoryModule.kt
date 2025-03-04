package com.example.secure_scan_app.shared.di

import com.example.secure_scan_app.data.repositories.QRRepositoryImpl
import com.example.secure_scan_app.domain.repositories.QRRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindQRRepository(
        impl: QRRepositoryImpl
    ): QRRepository
}
