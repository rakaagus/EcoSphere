package com.neirasphere.ecosphere.data.repository

import com.neirasphere.ecosphere.data.local.DataSource
import com.neirasphere.ecosphere.domain.model.EducationData
import com.neirasphere.ecosphere.domain.model.FifthEducationData
import com.neirasphere.ecosphere.domain.model.FirstEducationData
import com.neirasphere.ecosphere.domain.model.FourthEducationData
import com.neirasphere.ecosphere.domain.model.SecondEducationData
import com.neirasphere.ecosphere.domain.model.ThirdEducationData
import com.neirasphere.ecosphere.domain.repository.EducationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EducationRepositoryImpl @Inject constructor(
    private val dataSource: DataSource
) : EducationRepository{

    override val education = mutableListOf<EducationData>()
    override val firstEducationList = mutableListOf<FirstEducationData>()
    override val secondEducationList = mutableListOf<SecondEducationData>()
    override val thirdEducationList = mutableListOf<ThirdEducationData>()
    override val fourthEducationList = mutableListOf<FourthEducationData>()
    override val fifthEducationList = mutableListOf<FifthEducationData>()

    init{
        if(education.isEmpty()){
            dataSource.education().forEach{
                education.add(it)
            }
        }
        if(firstEducationList.isEmpty()){
            dataSource.firstEduList().forEach{
                firstEducationList.add(it)
            }
        }
        if(secondEducationList.isEmpty()){
            dataSource.secondEduList().forEach{
                secondEducationList.add(it)
            }
        }
        if(thirdEducationList.isEmpty()){
            dataSource.thirdEduList().forEach{
                thirdEducationList.add(it)
            }
        }
        if(fourthEducationList.isEmpty()){
            dataSource.fourthEduList().forEach{
                fourthEducationList.add(it)
            }
        }
        if(fifthEducationList.isEmpty()){
            dataSource.fifthEduList().forEach{
                fifthEducationList.add(it)
            }
        }
    }

    override fun getAllEducationData() : Flow<List<EducationData>> = flowOf(education)

    //fun getAllFirstEduData() : Flow<List<FirstEducationData>> = flowOf(firstEducationList)

    override fun getFirstEduById(educationId : Long): FirstEducationData {
        return firstEducationList.first{
            it.id == educationId
        }
    }

    override fun getSecondEduById(secEducationId : Long): SecondEducationData {
        return secondEducationList.first{
            it.id == secEducationId
        }
    }

    override fun getThirdEduById(thirdEducationId : Long): ThirdEducationData {
        return thirdEducationList.first{
            it.id == thirdEducationId
        }
    }

    override fun getFourthEduById(fourthEducationId : Long): FourthEducationData {
        return fourthEducationList.first{
            it.id == fourthEducationId
        }
    }

    override fun getFifthEduById(fifthEducationId : Long): FifthEducationData {
        return fifthEducationList.first{
            it.id == fifthEducationId
        }
    }
}