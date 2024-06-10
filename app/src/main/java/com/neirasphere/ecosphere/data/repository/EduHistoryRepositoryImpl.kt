package com.neirasphere.ecosphere.data.repository

import com.neirasphere.ecosphere.data.local.EduHistoryDao
import com.neirasphere.ecosphere.data.local.entities.EduHistory
import com.neirasphere.ecosphere.domain.repository.EduHistoryList
import com.neirasphere.ecosphere.domain.repository.EduHistoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EduHistoryRepositoryImpl @Inject constructor(
    private val eduHistoryDao: EduHistoryDao
) : EduHistoryRepository {
    override fun getEduHistory(): Flow<EduHistoryList> {
        return eduHistoryDao.getEduHistory()
    }

    override suspend fun getEduHistoryById(id: Int): EduHistory {
        return eduHistoryDao.getEduHistoryById(id)
    }

    override suspend fun addEduHistory(eduHistory: EduHistory) {
        eduHistoryDao.addEduHistory(eduHistory)
    }
}