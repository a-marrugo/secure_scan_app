package com.example.secure_scan_app.data.services.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.secure_scan_app.data.dto.QRDao
import com.example.secure_scan_app.domain.entities.QRDataEntity

/**
 * Singleton class representing the Room database for storing QR data.
 *
 * This database includes a single entity, [QRDataEntity], and provides access to the data
 * through the DAO [QRDao]. It follows the singleton pattern to ensure only one instance
 * of the database is created throughout the application's lifecycle.
 *
 * @Database annotation:
 * - Defines the database schema.
 * - Sets version to 1.
 * - Disables schema export by setting `exportSchema = false`.
 */
@Database(entities = [QRDataEntity::class], version = 1, exportSchema = false)
abstract class QRDatabase : RoomDatabase() {
    /**
     * Provides access to the DAO for interacting with QR data.
     *
     * @return An instance of [QRDao].
     */
    abstract fun qrDao(): QRDao

    companion object {
        @Volatile
        private var INSTANCE: QRDatabase? = null

        /**
         * Retrieves the singleton instance of the database.
         *
         * This method ensures thread safety by using a synchronized block, preventing multiple
         * instances from being created in a multi-threaded environment.
         *
         * @param context The application context used for initializing the database.
         * @return The single instance of [QRDatabase].
         */
        fun getDatabase(context: Context): QRDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QRDatabase::class.java,
                    "qr_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
