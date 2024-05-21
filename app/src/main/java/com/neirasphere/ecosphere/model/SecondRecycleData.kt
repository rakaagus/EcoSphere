package com.neirasphere.ecosphere.model

sealed class SecondRecycleData{
    data class Content (
        val id : Long,
        val title : String,
        val image : Int,
        val steps : List<Step>,
        val source : String,
    ) : SecondRecycleData()
}

data class Step(
    val stepNumber : Int,
    val stepTitle : String,
    val stepDescription : String,
    val stepImage : Int,
)