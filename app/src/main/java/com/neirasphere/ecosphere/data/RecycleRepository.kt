package com.neirasphere.ecosphere.data

import com.neirasphere.ecosphere.data.local.DataSource
import com.neirasphere.ecosphere.data.local.DataSource.secondRecycleList
import com.neirasphere.ecosphere.data.local.EducationRepository
import com.neirasphere.ecosphere.model.FirstEducationData
import com.neirasphere.ecosphere.model.FirstRecycleData
import com.neirasphere.ecosphere.model.RecycleCategoryData
import com.neirasphere.ecosphere.model.SecondRecycleData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import java.lang.IllegalArgumentException

class RecycleRepository {

    private val recycleCategory = mutableListOf<RecycleCategoryData>()
    private val firstRecycle = mutableListOf<FirstRecycleData>()
    private val secondRecycle = mutableListOf<SecondRecycleData>()


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

    fun getAllRecycleCategory() : Flow<List<RecycleCategoryData>> = flowOf(recycleCategory)

    fun getAllFirstRecycleList(): List<FirstRecycleData> {
        return firstRecycle
    }

    fun getFirstRecycleListByCategoryId(categoryId: Long): Flow<List<FirstRecycleData>> = flow {
        val category = DataSource.recycleCategory().find { it.id == categoryId }
        val filteredList = category?.contentId?.let { contentIds ->
            DataSource.categoryContentList().filter { it.id in contentIds }
        }.orEmpty()
        emit(filteredList)
    }

    fun getSecondRecycleListById(id: Int): Flow<List<SecondRecycleData>> = flow {
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