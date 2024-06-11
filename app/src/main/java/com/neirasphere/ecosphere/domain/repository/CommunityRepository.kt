package com.neirasphere.ecosphere.domain.repository

import com.neirasphere.ecosphere.ResultDefault
import com.neirasphere.ecosphere.data.remote.response.CommunityResponse
import com.neirasphere.ecosphere.domain.model.CommunityPost
import kotlinx.coroutines.flow.Flow

interface CommunityRepository {

    val communityPost : MutableList<CommunityPost>

    fun getAllCommunityPost() : Flow<List<CommunityPost>>

    fun post(content: String): Flow<ResultDefault<CommunityResponse>>
}