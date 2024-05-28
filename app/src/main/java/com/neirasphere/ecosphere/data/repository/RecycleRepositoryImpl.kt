package com.neirasphere.ecosphere.data.repository

import com.neirasphere.ecosphere.data.local.DataSource
import com.neirasphere.ecosphere.data.local.DataSource.secondRecycleList
import com.neirasphere.ecosphere.domain.model.FirstRecycleData
import com.neirasphere.ecosphere.domain.model.RecycleCategoryData
import com.neirasphere.ecosphere.domain.model.SecondRecycleData
import com.neirasphere.ecosphere.domain.repository.RecycleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecycleRepositoryImpl @Inject constructor(
    private val dataSource: DataSource
) : RecycleRepository{

    override val recycleCategory = mutableListOf<RecycleCategoryData>()
    override val firstRecycle = mutableListOf<FirstRecycleData>()
    override val secondRecycle = mutableListOf<SecondRecycleData>()


    init{
        if(recycleCategory.isEmpty()){
            dataSource.recycleCategory().forEach{
                recycleCategory.add(it)
            }
        }
    }

    override fun getAllRecycleCategory() : Flow<List<RecycleCategoryData>> = flowOf(recycleCategory)

    override fun getAllFirstRecycleList(): List<FirstRecycleData> {
        return firstRecycle
    }

    override fun getFirstRecycleListByCategoryId(categoryId: Long): Flow<List<FirstRecycleData>> = flow {
        val category = DataSource.recycleCategory().find { it.id == categoryId }
        val filteredList = category?.contentId?.let { contentIds ->
            DataSource.categoryContentList().filter { it.id in contentIds }
        }.orEmpty()
        emit(filteredList)
    }

    override fun getSecondRecycleListById(id: Int): Flow<List<SecondRecycleData>> = flow {
        val data = secondRecycleList(id)
        secondRecycle.addAll(listOf(data))

        emit(secondRecycle)
    }
}