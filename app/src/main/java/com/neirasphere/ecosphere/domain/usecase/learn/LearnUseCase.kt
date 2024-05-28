package com.neirasphere.ecosphere.domain.usecase.learn

import com.neirasphere.ecosphere.domain.model.CategoryLearn
import kotlinx.coroutines.flow.Flow

interface LearnUseCase {
    val homeCategoryLearn: List<Long>

    val learnHome: MutableList<CategoryLearn>
    fun getAllHomeLearnCategory(): Flow<List<CategoryLearn>>
}