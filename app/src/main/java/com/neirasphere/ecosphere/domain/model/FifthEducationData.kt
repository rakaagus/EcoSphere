package com.neirasphere.ecosphere.domain.model

data class FifthEducationData (
    val id : Long,
    val title : String,
    val firstDesc : String,
    val secondDesc : String?,
    val thirdDesc : String?,
    val fourthDesc : String?,
    val lastDesc : String,
    val firstImage : Int,
    val secondImage : Int?,
)