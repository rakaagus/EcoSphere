package com.neirasphere.ecosphere.data.remote.response

import com.google.gson.annotations.SerializedName

data class CommunityPostResponse(

    @field:SerializedName("message")
    val message: String

)

data class GetPostsResponse(

    @field:SerializedName("data")
    val data: List<DataItem?>? = null,
    @field:SerializedName("success")
    val success: Boolean? = null,
    @field:SerializedName("message")
    val message: String? = null
)

data class PostResponse(

    @field:SerializedName("message")
    val message: String,
    @field:SerializedName("success")
    val success: Boolean? = null

)

data class DataItem(

    @field:SerializedName("communityId")
    val communityId: Int? = null,
    @field:SerializedName("post")
    val post: String? = null,
    @field:SerializedName("post_img")
    val postImg: String? = null,
    @field:SerializedName("id_user")
    val idUser: Int? = null,
    @field:SerializedName("email")
    val email: String? = null,
    @field:SerializedName("img_profile")
    val imgProfile: String? = null,
    @field:SerializedName("created_at")
    val createdAt: String? = null

)

data class GetPostLikesResponse(

    @field:SerializedName("message")
    val message: String,
    @field:SerializedName("success")
    val success: Boolean? = null,
    @field:SerializedName("data")
    val data: List<LikeItem?>? = null

)

data class LikeItem(

    @field:SerializedName("likeId")
    val likeId: Int? = null,
    @field:SerializedName("communityId")
    val communityId: Int? = null,
    @field:SerializedName("id_user")
    val idUser: Int? = null,
    @field:SerializedName("email")
    val email: String? = null

)

data class GetPostCommentsResponse(
    val data: List<CommentItem?>? = null,
    val success: Boolean? = null,
    val message: String? = null
)

data class CommentItem(
    val commentImg: String? = null,
    val commentId: Int? = null,
    val createdAt: String? = null,
    val comment: String? = null,
    val idUser: Int? = null,
    val communityId: Int? = null,
    val email: String? = null
)

data class GetUserByIdResponse(
	@field:SerializedName("message")
	val message: String,
	@field:SerializedName("success")
	val success: Boolean,
	@field:SerializedName("data")
	val data: User
)