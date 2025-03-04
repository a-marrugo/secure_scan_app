package com.example.secure_scan_app.shared.di


import android.content.Context
import androidx.room.Room
import com.example.secure_scan_app.data.dto.QRDao
import com.example.secure_scan_app.data.services.local.QRDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): QRDatabase {
        return Room.databaseBuilder(
            context,
            QRDatabase::class.java,
            "qr_database"
        ).build()
    }

    @Provides
    fun provideQRDao(database: QRDatabase): QRDao {
        return database.qrDao()
    }
}
