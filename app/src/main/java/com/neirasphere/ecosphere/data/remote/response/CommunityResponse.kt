package com.neirasphere.ecosphere.data.remote.response

import com.google.gson.annotations.SerializedName

data class CommunityPostResponse(

    @field:SerializedName("message")
    val message: String

)

data class GetAllPostResponse(
    @field:SerializedName("data")
    val data: List<DataItem?>? = null,
    @field:SerializedName("message")
    val message: String? = null
)

data class DataItem(
    @field:SerializedName("post_img")
    val postImg: String? = null,
    @field:SerializedName("created_at")
    val createdAt: String? = null,
    @field:SerializedName("post")
    val post: String? = null,
    @field:SerializedName("id_user")
    val idUser: Int? = null,
    @field:SerializedName("communityId")
    val communityId: Int? = null,
    @field:SerializedName("email")
    val email: String? = null,
    @field:SerializedName("img_profile")
    val imgProfile: String? = null

)