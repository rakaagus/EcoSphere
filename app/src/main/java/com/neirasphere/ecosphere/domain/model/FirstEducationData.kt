package com.neirasphere.ecosphere.domain.model

data class FirstEducationData (
    val id : Long,
    val title : String,
    val desc : String,
    val secondDesc : String?,
    val thirdDesc : String?,
    val firstImage : Int,
    val secondImage : Int?,
)