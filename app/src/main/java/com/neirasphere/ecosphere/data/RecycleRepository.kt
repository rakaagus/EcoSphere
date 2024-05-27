package com.neirasphere.ecosphere.data

import com.neirasphere.ecosphere.data.local.DataSource
import com.neirasphere.ecosphere.data.local.DataSource.secondRecycleList
import com.neirasphere.ecosphere.domain.model.FirstRecycleData
import com.neirasphere.ecosphere.domain.model.RecycleCategoryData
import com.neirasphere.ecosphere.domain.model.SecondRecycleData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class RecycleRepository {

    private val recycleCategory = mutableListOf<com.neirasphere.ecosphere.domain.model.RecycleCategoryData>()
    private val firstRecycle = mutableListOf<com.neirasphere.ecosphere.domain.model.FirstRecycleData>()
    private val secondRecycle = mutableListOf<com.neirasphere.ecosphere.domain.model.SecondRecycleData>()


    init{
        if(recycleCategory.isEmpty()){
            DataSource.recycleCategory().forEach{
                recycleCategory.add(it)
            }
        }
//        if(secondRecycle.isEmpty()){
//            val data = DataSource.secondRecycleList(1)
//            secondRecycle.addAll(listOf(data))
//        }
    }

    fun getAllRecycleCategory() : Flow<List<com.neirasphere.ecosphere.domain.model.RecycleCategoryData>> = flowOf(recycleCategory)

    fun getAllFirstRecycleList(): List<com.neirasphere.ecosphere.domain.model.FirstRecycleData> {
        return firstRecycle
    }

    fun getFirstRecycleListByCategoryId(categoryId: Long): Flow<List<com.neirasphere.ecosphere.domain.model.FirstRecycleData>> = flow {
        val category = DataSource.recycleCategory().find { it.id == categoryId }
        val filteredList = category?.contentId?.let { contentIds ->
            DataSource.categoryContentList().filter { it.id in contentIds }
        }.orEmpty()
        emit(filteredList)
    }

    fun getSecondRecycleListById(id: Int): Flow<List<com.neirasphere.ecosphere.domain.model.SecondRecycleData>> = flow {
        val data = secondRecycleList(id)
        secondRecycle.addAll(listOf(data))

        emit(secondRecycle)
    }

    //ini diubah lagi keluarkan berdasarkan id yang di didapatkan dari firstrecycleId
//    fun getOrigamiData() : Flow<List<SecondRecycleData>> {
//      return flowOf(secondRecycle)
//    }

    companion object{
        @Volatile
        private var instance: RecycleRepository? = null

        fun getInstance(): RecycleRepository =
            instance ?: synchronized(this){
                RecycleRepository().apply {
                    instance = this
                }
            }
    }
}