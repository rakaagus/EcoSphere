package com.neirasphere.ecosphere.domain.repository

import com.neirasphere.ecosphere.data.local.DataSource
import com.neirasphere.ecosphere.domain.model.FirstRecycleData
import com.neirasphere.ecosphere.domain.model.RecycleCategoryData
import com.neirasphere.ecosphere.domain.model.SecondRecycleData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

interface RecycleRepository {

    val recycleCategory : MutableList<RecycleCategoryData>
    val firstRecycle : MutableList<FirstRecycleData>
    val secondRecycle : MutableList<SecondRecycleData>

    fun getAllRecycleCategory() : Flow<List<RecycleCategoryData>>

    fun getAllFirstRecycleList(): List<FirstRecycleData>

    fun getFirstRecycleListByCategoryId(categoryId: Long): Flow<List<FirstRecycleData>>

    fun getSecondRecycleListById(id: Int): Flow<List<SecondRecycleData>>

}