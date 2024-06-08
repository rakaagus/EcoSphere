package com.neirasphere.ecosphere.data.network

import androidx.room.Database
import androidx.room.RoomDatabase
import com.neirasphere.ecosphere.data.dao.EduHistoryDao
import com.neirasphere.ecosphere.domain.model.EduHistory

@Database(
    entities = [EduHistory::class],
    version = 1,
    exportSchema = false,
)
abstract class EduDb : RoomDatabase(){
    abstract val eduHistoryDao: EduHistoryDao
}