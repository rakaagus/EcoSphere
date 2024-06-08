package com.neirasphere.ecosphere.domain.repository

import com.neirasphere.ecosphere.domain.model.EduHistory
import kotlinx.coroutines.flow.Flow

typealias EduHistoryList = List<EduHistory>

interface EduHistoryRepository{
    fun getEduHistory(): Flow<EduHistoryList>

    suspend fun getEduHistoryById(id: Int): EduHistory

    suspend fun addEduHistory(eduHistory: EduHistory)
}