package com.neirasphere.ecosphere.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.neirasphere.ecosphere.data.local.entities.ClassifyHistory
import com.neirasphere.ecosphere.domain.repository.AllClassifyHistory
import kotlinx.coroutines.flow.Flow

@Dao
interface ClassifyHistoryDao {
    @Query("SELECT * FROM classify_history_table ORDER BY id ASC")
    fun getAllHistory(): Flow<AllClassifyHistory>

    @Query("SELECT * FROM classify_history_table WHERE id = :id")
    suspend fun getClassifyHistoryById(id: Int): ClassifyHistory

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addClassifyHistory(classifyHistory: ClassifyHistory)

    @Delete
    suspend fun deleteClassifyHistory(classifyHistory: ClassifyHistory)
}