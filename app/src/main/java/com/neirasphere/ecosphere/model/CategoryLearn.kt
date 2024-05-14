package com.neirasphere.ecosphere.model

import androidx.annotation.DrawableRes

data class CategoryLearn(
    val id: Long,
    val title: String,
    @DrawableRes val imageIcon: Int
)
