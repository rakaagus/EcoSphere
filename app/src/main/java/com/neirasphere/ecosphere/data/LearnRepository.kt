package com.neirasphere.ecosphere.data

import com.neirasphere.ecosphere.data.local.DataSource
import com.neirasphere.ecosphere.model.CategoryLearn
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class LearnRepository {
    private val homeCategoryLearn: List<Long> = listOf(
        1,2,3
    )

    private val learnHome = mutableListOf<CategoryLearn>()

    init {
        if(learnHome.isEmpty()){
            DataSource.categoryLearn().forEach {
                if(it.id in homeCategoryLearn){
                    learnHome.add(it)
                }
            }
        }
    }

    fun getAllHomeLearnCategory(): Flow<List<CategoryLearn>> =  flowOf(learnHome)

    companion object{
        @Volatile
        private var instance: LearnRepository? = null

        fun getInstance(): LearnRepository =
            instance ?: synchronized(this){
                LearnRepository().apply {
                    instance = this
                }
            }
    }
}