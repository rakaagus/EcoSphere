package com.neirasphere.ecosphere.data.repository

import com.neirasphere.ecosphere.data.local.DataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import com.neirasphere.ecosphere.domain.model.CategoryLearn
import com.neirasphere.ecosphere.domain.repository.LearnRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LearnRepositoryImpl @Inject constructor(
    private val dataSource: DataSource
): LearnRepository {
    override val homeCategoryLearn: List<Long> = listOf(
        1,2,3
    )

    override val learnHome = mutableListOf<CategoryLearn>()

    init {
        if(learnHome.isEmpty()){
            dataSource.categoryLearn().forEach {
                if(it.id in homeCategoryLearn){
                    learnHome.add(it)
                }
            }
        }
    }


    override fun getAllHomeLearnCategory(): Flow<List<CategoryLearn>> = flowOf(learnHome)
}