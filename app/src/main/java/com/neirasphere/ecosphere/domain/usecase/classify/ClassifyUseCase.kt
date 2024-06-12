package com.neirasphere.ecosphere.domain.usecase.classify

import com.neirasphere.ecosphere.ResultDefault
import com.neirasphere.ecosphere.data.ResultClassify
import com.neirasphere.ecosphere.data.remote.response.ClassifyResult
import kotlinx.coroutines.flow.Flow
import java.io.File

interface ClassifyUseCase {
    fun classifyTrash(file: File): Flow<ResultClassify<ClassifyResult>>
}