package com.neirasphere.ecosphere.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.neirasphere.ecosphere.data.local.ClassifyHistoryDao
import com.neirasphere.ecosphere.data.local.EduHistoryDao
import com.neirasphere.ecosphere.data.local.entities.ClassifyHistory
import com.neirasphere.ecosphere.data.local.entities.EduHistory

@Database(
    entities = [
        EduHistory::class,
        ClassifyHistory::class],
    version = 1,
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase(){
    abstract val eduHistoryDao: EduHistoryDao
    abstract val classifyHistoryDao: ClassifyHistoryDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}