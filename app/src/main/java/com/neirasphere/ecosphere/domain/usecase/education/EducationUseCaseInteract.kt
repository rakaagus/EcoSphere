package com.neirasphere.ecosphere.domain.usecase.education

import com.neirasphere.ecosphere.domain.model.EducationData
import com.neirasphere.ecosphere.domain.model.FifthEducationData
import com.neirasphere.ecosphere.domain.model.FirstEducationData
import com.neirasphere.ecosphere.domain.model.FourthEducationData
import com.neirasphere.ecosphere.domain.model.SecondEducationData
import com.neirasphere.ecosphere.domain.model.ThirdEducationData
import com.neirasphere.ecosphere.domain.repository.EducationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EducationUseCaseInteract @Inject constructor(
    private val repository: EducationRepository
) : EducationUseCase{
    override val education: MutableList<EducationData> = repository.education
    override val firstEducationList: MutableList<FirstEducationData> = repository.firstEducationList
    override val secondEducationList: MutableList<SecondEducationData> = repository.secondEducationList
    override val thirdEducationList: MutableList<ThirdEducationData> = repository.thirdEducationList
    override val fourthEducationList: MutableList<FourthEducationData> = repository.fourthEducationList
    override val fifthEducationList: MutableList<FifthEducationData> = repository.fifthEducationList

    override fun getAllEducationData(): Flow<List<EducationData>>  = repository.getAllEducationData()

    override fun getFirstEduById(educationId: Long): FirstEducationData  = repository.getFirstEduById(educationId)

    override fun getSecondEduById(secEducationId: Long): SecondEducationData = repository.getSecondEduById(secEducationId)

    override fun getThirdEduById(thirdEducationId: Long): ThirdEducationData = repository.getThirdEduById(thirdEducationId)

    override fun getFourthEduById(fourthEducationId: Long): FourthEducationData = repository.getFourthEduById(fourthEducationId)

    override fun getFifthEduById(fifthEducationId: Long): FifthEducationData = repository.getFifthEduById(fifthEducationId)
}