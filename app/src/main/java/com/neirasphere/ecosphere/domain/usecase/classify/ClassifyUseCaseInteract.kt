package com.neirasphere.ecosphere.domain.usecase.classify

import com.neirasphere.ecosphere.ResultDefault
import com.neirasphere.ecosphere.data.ResultClassify
import com.neirasphere.ecosphere.data.remote.response.ClassifyResult
import com.neirasphere.ecosphere.domain.repository.ClassificationRepository
import kotlinx.coroutines.flow.Flow
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ClassifyUseCaseInteract @Inject constructor(private val repository: ClassificationRepository) : ClassifyUseCase{
    override fun classifyTrash(file: File): Flow<ResultClassify<ClassifyResult>> = repository.classifyTrash(file)

}