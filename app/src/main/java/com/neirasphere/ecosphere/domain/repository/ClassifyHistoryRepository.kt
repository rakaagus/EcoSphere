package com.neirasphere.ecosphere.domain.repository

import com.neirasphere.ecosphere.data.local.entities.ClassifyHistory
import kotlinx.coroutines.flow.Flow

typealias AllClassifyHistory = List<ClassifyHistory>

interface ClassifyHistoryRepository{
    fun getAllClassifyHistory(): Flow<AllClassifyHistory>

    suspend fun getClassifyHistoryById(id: Int): ClassifyHistory

    suspend fun addClassifyHistory(classifyHistory: ClassifyHistory)
}