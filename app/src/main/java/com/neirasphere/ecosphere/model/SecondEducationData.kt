package com.neirasphere.ecosphere.model

data class SecondEducationData (
    val id : Long,
    val title : String,
    val desc : String,
    val secondDesc : String?,
    val thirdDesc : String?,
    val firstImage : Int,
    val secondImage : Int?,
    val thirdImage : Int?,
)