package com.neirasphere.ecosphere.data.local

import com.neirasphere.ecosphere.R
import com.neirasphere.ecosphere.model.CategoryLearn
import com.neirasphere.ecosphere.model.CommunityPost
import com.neirasphere.ecosphere.model.User

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

    fun communityPostUser(): List<User> = listOf(
        User(
            1,
            "Indah Nur Lailatul",
            "indahnurlailatul11",
            R.drawable.example_image_user
        )
    )

    fun communityPostData(): List<CommunityPost> = listOf(
        CommunityPost(
            1,
            communityPostUser()[0],
            "Aku kemarin bikin pot dari handuk bekas hasilnya bagus banget",
            R.drawable.pot_handuk_2,
            1700,
            20000,
            true,
            1900000,
            "2024-05-19 18:59:00"
        ),
        CommunityPost(
            2,
            communityPostUser()[0],
            "Aku kemarin bikin pot dari handuk bekas hasilnya bagus banget",
            R.drawable.pot_handuk_2,
            1700,
            20000,
            true,
            1900000,
            "2024-05-19 18:59:00"
        ),
        CommunityPost(
            3,
            communityPostUser()[0],
            "Aku kemarin bikin pot dari handuk bekas hasilnya bagus banget",
            R.drawable.pot_handuk_2,
            1700,
            20000,
            true,
            1900000,
            "2024-05-19 18:59:00"
        ),
        CommunityPost(
            4,
            communityPostUser()[0],
            "Aku kemarin bikin pot dari handuk bekas hasilnya bagus banget",
            R.drawable.pot_handuk_2,
            1700,
            20000,
            true,
            1900000,
            "2024-05-19 18:59:00"
        ),
        CommunityPost(
            5,
            communityPostUser()[0],
            "Pot daur ulang ini sangat bagus dan berkualitas sekali, tidak kalah dengan yang baru",
            R.drawable.pot_handuk_2,
            1700,
            10000,
            true,
            1900000,
            "2024-05-20 18:59:00"
        ),
        CommunityPost(
            6,
            communityPostUser()[0],
            "Pot daur ulang ini sangat bagus dan berkualitas sekali, tidak kalah dengan yang baru",
            R.drawable.pot_handuk_2,
            1700,
            10000,
            true,
            1900000,
            "2024-05-20 18:59:00"
        ),
        CommunityPost(
            7,
            communityPostUser()[0],
            "Pot daur ulang ini sangat bagus dan berkualitas sekali, tidak kalah dengan yang baru",
            R.drawable.pot_handuk_2,
            1700,
            10000,
            true,
            1900000,
            "2024-05-20 18:59:00"
        ),
        CommunityPost(
            8,
            communityPostUser()[0],
            "Pot daur ulang ini sangat bagus dan berkualitas sekali, tidak kalah dengan yang baru",
            R.drawable.pot_handuk_2,
            1700,
            10000,
            true,
            1900000,
            "2024-05-20 18:59:00"
        ),
    )
}