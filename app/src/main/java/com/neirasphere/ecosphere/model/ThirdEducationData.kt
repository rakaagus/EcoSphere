package com.neirasphere.ecosphere.model

data class ThirdEducationData (
    val id : Long,
    val title : String,
    val desc : String,
    val secondDesc : String?,
    val thirdDesc : String?,
    val fourthDesc : String?,
    val fifthDesc : String?,
    val firstImage : Int,
    val secondImage : Int?,
    val thirdImage : Int?,
    val fourthImage : Int?,
)