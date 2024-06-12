package com.neirasphere.ecosphere.data.repository

import com.neirasphere.ecosphere.data.ResultClassify
import com.neirasphere.ecosphere.data.remote.RemoteDataSource
import com.neirasphere.ecosphere.data.remote.response.ClassifyResult
import com.neirasphere.ecosphere.domain.repository.ClassificationRepository
import kotlinx.coroutines.Dispatchers
import java.io.File
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ClassificationRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : ClassificationRepository {
    override fun classifyTrash(file: File): Flow<ResultClassify<ClassifyResult>> = flow<ResultClassify<ClassifyResult>> {
        emit(ResultClassify.Loading())
        try {
            val response = remoteDataSource.classifyTrash(file)
            emit(ResultClassify.Success(response))
        }catch (e: Exception){
            emit(ResultClassify.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)


}