package com.neirasphere.ecosphere.domain.repository

import com.neirasphere.ecosphere.domain.model.CategoryLearn
import kotlinx.coroutines.flow.Flow

interface LearnRepository {
    val homeCategoryLearn: List<Long>

    val learnHome: MutableList<CategoryLearn>
    fun getAllHomeLearnCategory(): Flow<List<CategoryLearn>>
}