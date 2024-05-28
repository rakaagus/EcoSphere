package com.neirasphere.ecosphere.domain.usecase.recycle

import com.neirasphere.ecosphere.domain.model.FirstRecycleData
import com.neirasphere.ecosphere.domain.model.RecycleCategoryData
import com.neirasphere.ecosphere.domain.model.SecondRecycleData
import kotlinx.coroutines.flow.Flow

interface RecycleUseCase {
    val recycleCategory : MutableList<RecycleCategoryData>
    val firstRecycle : MutableList<FirstRecycleData>
    val secondRecycle : MutableList<SecondRecycleData>

    fun getAllRecycleCategory() : Flow<List<RecycleCategoryData>>

    fun getAllFirstRecycleList(): List<FirstRecycleData>

    fun getFirstRecycleListByCategoryId(categoryId: Long): Flow<List<FirstRecycleData>>

    fun getSecondRecycleListById(id: Int): Flow<List<SecondRecycleData>>
}