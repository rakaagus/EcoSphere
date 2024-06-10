package com.neirasphere.ecosphere.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.neirasphere.ecosphere.domain.model.EduHistory
import com.neirasphere.ecosphere.domain.repository.EduHistoryList
import kotlinx.coroutines.flow.Flow

@Dao
interface EduHistoryDao {
    @Query("SELECT * FROM edu_history_table ORDER BY id ASC")
    fun getEduHistory(): Flow<EduHistoryList>

    @Query("SELECT * FROM edu_history_table WHERE id = :id")
    suspend fun getEduHistoryById(id: Int): EduHistory

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addEduHistory(eduHistory: EduHistory)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEduHistory(eduHistory: EduHistory)

    @Delete
    suspend fun deleteEduHistory(eduHistory: EduHistory)
}