package com.neirasphere.ecosphere.domain.usecase.learn

import com.neirasphere.ecosphere.domain.model.CategoryLearn
import com.neirasphere.ecosphere.domain.repository.LearnRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LearnUseCaseInteract @Inject constructor(
    private val learnRepository: LearnRepository
) : LearnUseCase {
    override val homeCategoryLearn: List<Long> = learnRepository.homeCategoryLearn

    override val learnHome: MutableList<CategoryLearn> = learnRepository.learnHome

    override fun getAllHomeLearnCategory(): Flow<List<CategoryLearn>> = learnRepository.getAllHomeLearnCategory()
}