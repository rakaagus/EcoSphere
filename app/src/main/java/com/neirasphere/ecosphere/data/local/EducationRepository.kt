package com.neirasphere.ecosphere.data.local

import com.neirasphere.ecosphere.model.EducationData
import com.neirasphere.ecosphere.model.FifthEducationData
import com.neirasphere.ecosphere.model.FirstEducationData
import com.neirasphere.ecosphere.model.FourthEducationData
import com.neirasphere.ecosphere.model.SecondEducationData
import com.neirasphere.ecosphere.model.ThirdEducationData
import com.neirasphere.ecosphere.ui.screen.education.FifthEducationViewModel
import com.neirasphere.ecosphere.ui.screen.education.FourthEducationViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf

class EducationRepository {

    private val education = mutableListOf<EducationData>()
    private val firstEducationList = mutableListOf<FirstEducationData>()
    private val secondEducationList = mutableListOf<SecondEducationData>()
    private val thirdEducationList = mutableListOf<ThirdEducationData>()
    private val fourthEducationList = mutableListOf<FourthEducationData>()
    private val fifthEducationList = mutableListOf<FifthEducationData>()

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

    fun getAllEducationData() : Flow<List<EducationData>> = flowOf(education)

    //fun getAllFirstEduData() : Flow<List<FirstEducationData>> = flowOf(firstEducationList)

    fun getFirstEduById(educationId : Long): FirstEducationData{
        return firstEducationList.first{
            it.id == educationId
        }
    }

    fun getSecondEduById(secEducationId : Long): SecondEducationData{
        return secondEducationList.first{
            it.id == secEducationId
        }
    }

    fun getThirdEduById(thirdEducationId : Long): ThirdEducationData{
        return thirdEducationList.first{
            it.id == thirdEducationId
        }
    }

    fun getFourthEduById(fourthEducationId : Long): FourthEducationData{
        return fourthEducationList.first{
            it.id == fourthEducationId
        }
    }

    fun getFifthEduById(fifthEducationId : Long): FifthEducationData{
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