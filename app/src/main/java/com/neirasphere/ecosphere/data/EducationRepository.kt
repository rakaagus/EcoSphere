package com.neirasphere.ecosphere.data

import com.neirasphere.ecosphere.data.local.DataSource
import com.neirasphere.ecosphere.domain.model.EducationData
import com.neirasphere.ecosphere.domain.model.FifthEducationData
import com.neirasphere.ecosphere.domain.model.FirstEducationData
import com.neirasphere.ecosphere.domain.model.FourthEducationData
import com.neirasphere.ecosphere.domain.model.SecondEducationData
import com.neirasphere.ecosphere.domain.model.ThirdEducationData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class EducationRepository {

    private val education = mutableListOf<com.neirasphere.ecosphere.domain.model.EducationData>()
    private val firstEducationList = mutableListOf<com.neirasphere.ecosphere.domain.model.FirstEducationData>()
    private val secondEducationList = mutableListOf<com.neirasphere.ecosphere.domain.model.SecondEducationData>()
    private val thirdEducationList = mutableListOf<com.neirasphere.ecosphere.domain.model.ThirdEducationData>()
    private val fourthEducationList = mutableListOf<com.neirasphere.ecosphere.domain.model.FourthEducationData>()
    private val fifthEducationList = mutableListOf<com.neirasphere.ecosphere.domain.model.FifthEducationData>()

    init{
        if(education.isEmpty()){
            DataSource.education().forEach{
                education.add(it)
            }
        }
        if(firstEducationList.isEmpty()){
            DataSource.firstEduList().forEach{
                firstEducationList.add(it)
            }
        }
        if(secondEducationList.isEmpty()){
            DataSource.secondEduList().forEach{
                secondEducationList.add(it)
            }
        }
        if(thirdEducationList.isEmpty()){
            DataSource.thirdEduList().forEach{
                thirdEducationList.add(it)
            }
        }
        if(fourthEducationList.isEmpty()){
            DataSource.fourthEduList().forEach{
                fourthEducationList.add(it)
            }
        }
        if(fifthEducationList.isEmpty()){
            DataSource.fifthEduList().forEach{
                fifthEducationList.add(it)
            }
        }
    }

    fun getAllEducationData() : Flow<List<com.neirasphere.ecosphere.domain.model.EducationData>> = flowOf(education)

    //fun getAllFirstEduData() : Flow<List<FirstEducationData>> = flowOf(firstEducationList)

    fun getFirstEduById(educationId : Long): com.neirasphere.ecosphere.domain.model.FirstEducationData {
        return firstEducationList.first{
            it.id == educationId
        }
    }

    fun getSecondEduById(secEducationId : Long): com.neirasphere.ecosphere.domain.model.SecondEducationData {
        return secondEducationList.first{
            it.id == secEducationId
        }
    }

    fun getThirdEduById(thirdEducationId : Long): com.neirasphere.ecosphere.domain.model.ThirdEducationData {
        return thirdEducationList.first{
            it.id == thirdEducationId
        }
    }

    fun getFourthEduById(fourthEducationId : Long): com.neirasphere.ecosphere.domain.model.FourthEducationData {
        return fourthEducationList.first{
            it.id == fourthEducationId
        }
    }

    fun getFifthEduById(fifthEducationId : Long): com.neirasphere.ecosphere.domain.model.FifthEducationData {
        return fifthEducationList.first{
            it.id == fifthEducationId
        }
    }

    companion object{
        @Volatile
        private var instance: EducationRepository? = null

        fun getInstance(): EducationRepository =
            instance ?: synchronized(this){
                EducationRepository().apply {
                    instance = this
                }
            }
    }
}