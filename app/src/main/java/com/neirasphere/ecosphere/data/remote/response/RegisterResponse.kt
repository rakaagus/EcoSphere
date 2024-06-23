package com.neirasphere.ecosphere.data.remote.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

	@field:SerializedName("data")
	val data: RegisterData,

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("message")
	val message: String
)

data class RegisterData(

	@field:SerializedName("nama_depan")
	val namaDepan: String,

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("nama_belakang")
	val namaBelakang: String,

	@field:SerializedName("email")
	val email: String
)
