package com.neirasphere.ecosphere.model

data class RecycleCategoryData (
    val id : Long,
    val title : String,
    val image : Int,
    val contentId : List<Int>,
)