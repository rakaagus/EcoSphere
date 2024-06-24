package com.neirasphere.ecosphere.data.repository

import com.neirasphere.ecosphere.data.local.ClassifyHistoryDao
import com.neirasphere.ecosphere.data.local.entities.ClassifyHistory
import com.neirasphere.ecosphere.domain.repository.AllClassifyHistory
import com.neirasphere.ecosphere.domain.repository.ClassifyHistoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ClassifyHistoryRepositoryImpl @Inject constructor(

    private val classifyHistoryDao: ClassifyHistoryDao

) : ClassifyHistoryRepository {

    override fun getAllClassifyHistory(): Flow<AllClassifyHistory> {
        return classifyHistoryDao.getAllHistory()
    }

    override suspend fun getClassifyHistoryById(id: Int): ClassifyHistory {
        return classifyHistoryDao.getClassifyHistoryById(id)
    }

    override suspend fun addClassifyHistory(classifyHistory: ClassifyHistory) {
        classifyHistoryDao.addClassifyHistory(classifyHistory)
    }
}