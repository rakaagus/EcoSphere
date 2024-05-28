package com.neirasphere.ecosphere.domain.repository

import com.neirasphere.ecosphere.domain.model.EducationData
import com.neirasphere.ecosphere.domain.model.FifthEducationData
import com.neirasphere.ecosphere.domain.model.FirstEducationData
import com.neirasphere.ecosphere.domain.model.FourthEducationData
import com.neirasphere.ecosphere.domain.model.SecondEducationData
import com.neirasphere.ecosphere.domain.model.ThirdEducationData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

interface EducationRepository {

     val education : MutableList<EducationData>
     val firstEducationList : MutableList<FirstEducationData>
     val secondEducationList:  MutableList<SecondEducationData>
     val thirdEducationList : MutableList<ThirdEducationData>
     val fourthEducationList : MutableList<FourthEducationData>
     val fifthEducationList : MutableList<FifthEducationData>

    fun getAllEducationData() : Flow<List<EducationData>>

    fun getFirstEduById(educationId : Long): FirstEducationData

    fun getSecondEduById(secEducationId : Long): SecondEducationData

    fun getThirdEduById(thirdEducationId : Long): ThirdEducationData

    fun getFourthEduById(fourthEducationId : Long): FourthEducationData

    fun getFifthEduById(fifthEducationId : Long): FifthEducationData
    
}