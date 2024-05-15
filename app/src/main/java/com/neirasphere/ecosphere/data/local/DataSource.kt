package com.neirasphere.ecosphere.data.local

import com.neirasphere.ecosphere.R
import com.neirasphere.ecosphere.model.CategoryLearn

object DataSource {
    fun categoryLearn(): List<CategoryLearn> = listOf(
        CategoryLearn(
            1,
            "Sampah Anorganik",
            R.drawable.item_home_1
        ),
        CategoryLearn(
            2,
            "Mengolah Sampah Anorganik",
            R.drawable.item_home_2
        ),
        CategoryLearn(
            3,
            "Mengolah Sampah Organik",
            R.drawable.item_home_3
        )
    )
}