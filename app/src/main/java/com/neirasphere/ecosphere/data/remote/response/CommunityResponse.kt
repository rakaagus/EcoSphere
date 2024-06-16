package com.neirasphere.ecosphere.data.remote.response

import com.google.gson.annotations.SerializedName

data class CommunityPostResponse(

    @field:SerializedName("message")
    val message: String

)

data class GetAllPostResponse(
    val data: List<DataItem?>? = null,
    val message: String? = null
)

data class PostImg(
    val data: List<Int?>? = null,
    val type: String? = null
)

data class DataItem(
    val postImg: PostImg? = null,
    val post: String? = null,
    val idUser: Int? = null,
    val communityId: Int? = null,
    val email: String? = null
)