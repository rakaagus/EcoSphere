package com.neirasphere.ecosphere.data.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("message")
	val message: String
)

data class GetUserByIdResponse(
	@field:SerializedName("message")
	val message: String,
	@field:SerializedName("success")
	val success: Boolean,
	@field:SerializedName("data")
	val data: User
)

data class User(

	@field:SerializedName("nama_depan")
	val namaDepan: String,

	@field:SerializedName("resetPasswordExpires")
	val resetPasswordExpires: Any,

	@field:SerializedName("role")
	val role: String,

	@field:SerializedName("img_profile")
	val imgProfile: String,

	@field:SerializedName("id_user")
	val idUser: Int,

	@field:SerializedName("resetPasswordOTP")
	val resetPasswordOTP: Any,

	@field:SerializedName("nama_belakang")
	val namaBelakang: String,

	@field:SerializedName("email")
	val email: String
)

data class Data(

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("user")
	val user: User,

	@field:SerializedName("token")
	val token: String
)
