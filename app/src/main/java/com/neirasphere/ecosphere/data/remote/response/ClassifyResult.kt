package com.neirasphere.ecosphere.data.remote.response

import com.google.gson.annotations.SerializedName

data class ClassifyResult(

	@field:SerializedName("class_category")
	val classCategory: String,

	@field:SerializedName("accuracy")
	val accuracy: String,

	@field:SerializedName("description")
	val description: String
)
