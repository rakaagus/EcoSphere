package com.neirasphere.ecosphere.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.neirasphere.ecosphere.data.local.EduHistoryDao
import com.neirasphere.ecosphere.data.local.entities.EduHistory

@Database(
    entities = [EduHistory::class],
    version = 1,
    exportSchema = false,
)
abstract class EduDb : RoomDatabase(){
    abstract val eduHistoryDao: EduHistoryDao
}