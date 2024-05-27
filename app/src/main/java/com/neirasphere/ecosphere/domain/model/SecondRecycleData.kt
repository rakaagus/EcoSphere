package com.neirasphere.ecosphere.domain.model

sealed class SecondRecycleData{
    data class Content (
        val id : Long,
        val title : String,
        val image : Int,
        val steps : List<com.neirasphere.ecosphere.domain.model.Step>,
        val source : String,
    ) : com.neirasphere.ecosphere.domain.model.SecondRecycleData()
}

data class Step(
    val stepNumber : Int,
    val stepTitle : String,
    val stepDescription : String,
    val stepImage : Int,
)