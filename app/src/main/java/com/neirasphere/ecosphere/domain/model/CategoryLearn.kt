package com.neirasphere.ecosphere.domain.model

import androidx.annotation.DrawableRes

data class CategoryLearn(
    val id: Long,
    val title: String,
    @DrawableRes val imageIcon: Int
)
