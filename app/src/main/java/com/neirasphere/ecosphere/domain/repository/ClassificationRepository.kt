package com.neirasphere.ecosphere.domain.repository


import com.neirasphere.ecosphere.data.ResultClassify
import com.neirasphere.ecosphere.data.remote.response.ClassifyResult
import java.io.File
import kotlinx.coroutines.flow.Flow

interface ClassificationRepository {
    fun classifyTrash(file: File): Flow<ResultClassify<ClassifyResult>>
}