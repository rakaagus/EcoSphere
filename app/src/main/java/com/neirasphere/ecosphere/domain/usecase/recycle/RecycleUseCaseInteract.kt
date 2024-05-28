package com.neirasphere.ecosphere.domain.usecase.recycle

import com.neirasphere.ecosphere.domain.model.FirstRecycleData
import com.neirasphere.ecosphere.domain.model.RecycleCategoryData
import com.neirasphere.ecosphere.domain.model.SecondRecycleData
import com.neirasphere.ecosphere.domain.repository.RecycleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecycleUseCaseInteract @Inject constructor(
    private val repository: RecycleRepository
) : RecycleUseCase{
    override val recycleCategory: MutableList<RecycleCategoryData> = repository.recycleCategory
    override val firstRecycle: MutableList<FirstRecycleData> = repository.firstRecycle
    override val secondRecycle: MutableList<SecondRecycleData> = repository.secondRecycle

    override fun getAllRecycleCategory(): Flow<List<RecycleCategoryData>> = repository.getAllRecycleCategory()

    override fun getAllFirstRecycleList(): List<FirstRecycleData> = repository.getAllFirstRecycleList()

    override fun getFirstRecycleListByCategoryId(categoryId: Long): Flow<List<FirstRecycleData>> = repository.getFirstRecycleListByCategoryId(categoryId)

    override fun getSecondRecycleListById(id: Int): Flow<List<SecondRecycleData>> =  repository.getSecondRecycleListById(id)
}