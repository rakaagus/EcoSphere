package com.neirasphere.ecosphere.data.local

import com.google.android.gms.maps.model.LatLng
import com.neirasphere.ecosphere.R
import com.neirasphere.ecosphere.model.CategoryLearn
import com.neirasphere.ecosphere.model.CommunityPost
import com.neirasphere.ecosphere.model.User
import com.neirasphere.ecosphere.model.EducationData
import com.neirasphere.ecosphere.model.FifthEducationData
import com.neirasphere.ecosphere.model.FirstEducationData
import com.neirasphere.ecosphere.model.FirstRecycleData
import com.neirasphere.ecosphere.model.FourthEducationData
import com.neirasphere.ecosphere.model.RecycleCategoryData
import com.neirasphere.ecosphere.model.MapData
import com.neirasphere.ecosphere.model.SecondEducationData
import com.neirasphere.ecosphere.model.SecondRecycleData
import com.neirasphere.ecosphere.model.Step
import com.neirasphere.ecosphere.model.ThirdEducationData

object DataSource {

    fun tpsMapData(): List<MapData> = listOf(
        MapData(
            id = 1,
            latLong = LatLng(-7.873, 110.320),
            title = "Tpst Piyungan",
            description = "TPS utama di daerah Puyungan dengan fasilitas pemrosesan sampah modern, termasuk pemilahan sampah organik dan anorganik, serta fasilitas daur ulang. TPS ini dilengkapi dengan teknologi ramah lingkungan untuk mengurangi emisi gas rumah kaca.",
            openLocation = listOf(
                "Senin - Jumat: 07:00 - 17:00",
                "Sabtu: 08:00 - 16:00",
                "Minggu: Tutup"
            ),
            detailLocation = "Jalan Raya Puyungan No. 1, Desa Puyungan, Kabupaten Bantul, Yogyakarta.",
            rating = 4.5,
            facilities = listOf(
                "Area pemilahan sampah",
                "Fasilitas daur ulang",
                "Pusat edukasi pengelolaan sampah",
                "Alat berat untuk pemrosesan sampah",
                "Sistem pengolahan air limbah"
            ),
            image = listOf(
                R.drawable.puyungan_2,
                R.drawable.puyungan_1,
            ),
            trashVariantAvailable = listOf(
                "Sampah organik",
                "Sampah anorganik",
                "Sampah plastik",
                "Kertas dan karton",
                "Logam dan kaca"
            )
        ),
        MapData(
            id = 2,
            latLong = LatLng(-7.865, 110.350),
            title = "TPS 3R Brama Muda",
            description = "TPS 3R (Reduce, Reuse, Recycle) Brama Muda merupakan fasilitas pengelolaan sampah yang berfokus pada pengurangan, penggunaan kembali, dan daur ulang. TPS ini melayani masyarakat sekitar dengan berbagai program pengelolaan sampah berkelanjutan dan edukasi lingkungan.",
            openLocation = listOf(
                "Senin - Jumat: 07:00 - 17:00",
                "Sabtu: 08:00 - 16:00",
                "Minggu: Tutup"
            ),
            detailLocation = "Jalan Lingkar Selatan No. 20, Desa Brama, Kabupaten Bantul, Yogyakarta.",
            rating = 4.6,
            facilities = listOf(
                "Area pemilahan sampah",
                "Fasilitas daur ulang",
                "Pusat edukasi pengelolaan sampah",
                "Alat berat untuk pemrosesan sampah",
                "Sistem pengolahan air limbah",
                "Komposter untuk pengolahan sampah organik",
                "Bank sampah",
                "Area pembuangan sementara"
            ),
            image = listOf(
                R.drawable.brama_muda_1,
                R.drawable.brama_muda_2,
            ),
            trashVariantAvailable = listOf(
                "Sampah organik",
                "Sampah anorganik",
                "Sampah plastik",
                "Kertas dan karton",
                "Logam dan kaca",
                "Sampah elektronik kecil (e-waste)",
                "Sampah tekstil"
            )
        ),
        MapData(
            id = 3,
            latLong = LatLng(-7.758, 110.381),
            title = "TPST Condongcatur",
            description = "TPST Condongcatur merupakan fasilitas pengolahan sampah terpadu yang melayani masyarakat sekitar dengan fokus pada pengurangan, penggunaan kembali, dan daur ulang sampah. TPST ini dilengkapi dengan teknologi modern untuk memproses sampah secara efisien dan ramah lingkungan. TPST ini juga berperan sebagai pusat edukasi dan pelatihan pengelolaan sampah bagi masyarakat.",
            openLocation = listOf(
                "Senin - Jumat: 07:00 - 17:00",
                "Sabtu: 08:00 - 16:00",
                "Minggu: 08:00 - 14:00"
            ),
            detailLocation = "Jalan Kaliurang Km. 5, Desa Condongcatur, Kabupaten Sleman, Yogyakarta.",
            rating = 4.7,
            facilities = listOf(
                "Area pemilahan sampah",
                "Fasilitas daur ulang",
                "Pusat edukasi pengelolaan sampah",
                "Alat berat untuk pemrosesan sampah",
                "Sistem pengolahan air limbah",
                "Mesin pencacah plastik",
                "Bank sampah",
                "Area pembuangan sementara",
                "Taman edukasi lingkungan"
            ),
            image = listOf(
                R.drawable.condongcatur_1,
            ),
            trashVariantAvailable = listOf(
                "Sampah organik",
                "Sampah anorganik",
                "Sampah plastik",
                "Kertas dan karton",
                "Logam dan kaca",
                "Sampah elektronik kecil (e-waste)",
                "Sampah tekstil",
                "Sampah rumah tangga",
                "Sampah komersial"
            )
        ),
    )

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

    fun fifthEduList() : List<FifthEducationData> = listOf(
        FifthEducationData(
            1,
            "Dampak Sampah Anorganik",
            "Dampak dari pencemaran lingkungan yang disebabkan oleh sampah anorganik dapat merusak ekosistem alami. Ini dapat mempengaruhi keberlanjutan sumber daya alam, merusak habitat satwa liar, dan mengancam keberlangsungan spesies tertentu.",
            "Peningkatan jumlah sampah anorganik dapat mengganggu keseimbangan ekosistem, baik secara langsung maupun tidak langsung.",
            "Dengan memahami dampak dari sampah anorganik, penting untuk mengambil langkah-langkah untuk mengurangi penggunaannya, mendaur ulang sebanyak mungkin, dan mengelola dengan bijaksana agar dapat menjaga kelestarian lingkungan dan kesehatan manusia.",
            "Jadi, apakah kamu siap menjadi bagian dari agen perubahan bumi yang lebih baik? ",
            "Mulailah memilah sampah dengan baik pada tempatnya dan membangun kesadaran diri dari sekarang. Kalau bukan kamu yang mulai, harus tunggu siapa lagi? ",
            R.drawable.edu_detail_5_1,
            R.drawable.edu_detail_5_2,
        ),
        FifthEducationData(
            2,
            "Dampak Sampah Organik",
            "Dampak dari pencemaran lingkungan yang disebabkan oleh sampah anorganik dapat merusak ekosistem alami. Ini dapat mempengaruhi keberlanjutan sumber daya alam, merusak habitat satwa liar, dan mengancam keberlangsungan spesies tertentu.",
            "Peningkatan jumlah sampah anorganik dapat mengganggu keseimbangan ekosistem, baik secara langsung maupun tidak langsung.",
            "Dengan memahami dampak dari sampah anorganik, penting untuk mengambil langkah-langkah untuk mengurangi penggunaannya, mendaur ulang sebanyak mungkin, dan mengelola dengan bijaksana agar dapat menjaga kelestarian lingkungan dan kesehatan manusia.",
            "Jadi, apakah kamu siap menjadi bagian dari agen perubahan bumi yang lebih baik? ",
            "Mulailah memilah sampah dengan baik pada tempatnya dan membangun kesadaran diri dari sekarang. Kalau bukan kamu yang mulai, harus tunggu siapa lagi? ",
            R.drawable.edu_detail_5_1,
            R.drawable.edu_detail_5_2,
        ),
        FifthEducationData(
            3,
            "Dampak Sampah B3",
            "Dampak dari pencemaran lingkungan yang disebabkan oleh sampah anorganik dapat merusak ekosistem alami. Ini dapat mempengaruhi keberlanjutan sumber daya alam, merusak habitat satwa liar, dan mengancam keberlangsungan spesies tertentu.",
            "Peningkatan jumlah sampah anorganik dapat mengganggu keseimbangan ekosistem, baik secara langsung maupun tidak langsung.",
            "Dengan memahami dampak dari sampah anorganik, penting untuk mengambil langkah-langkah untuk mengurangi penggunaannya, mendaur ulang sebanyak mungkin, dan mengelola dengan bijaksana agar dapat menjaga kelestarian lingkungan dan kesehatan manusia.",
            "Jadi, apakah kamu siap menjadi bagian dari agen perubahan bumi yang lebih baik? ",
            "Mulailah memilah sampah dengan baik pada tempatnya dan membangun kesadaran diri dari sekarang. Kalau bukan kamu yang mulai, harus tunggu siapa lagi? ",
            R.drawable.edu_detail_5_1,
            R.drawable.edu_detail_5_2,
        ),
        FifthEducationData(
            4,
            "Dampak Sampah Kertas",
            "Dampak dari pencemaran lingkungan yang disebabkan oleh sampah anorganik dapat merusak ekosistem alami. Ini dapat mempengaruhi keberlanjutan sumber daya alam, merusak habitat satwa liar, dan mengancam keberlangsungan spesies tertentu.",
            "Peningkatan jumlah sampah anorganik dapat mengganggu keseimbangan ekosistem, baik secara langsung maupun tidak langsung.",
            "Dengan memahami dampak dari sampah anorganik, penting untuk mengambil langkah-langkah untuk mengurangi penggunaannya, mendaur ulang sebanyak mungkin, dan mengelola dengan bijaksana agar dapat menjaga kelestarian lingkungan dan kesehatan manusia.",
            "Jadi, apakah kamu siap menjadi bagian dari agen perubahan bumi yang lebih baik? ",
            "Mulailah memilah sampah dengan baik pada tempatnya dan membangun kesadaran diri dari sekarang. Kalau bukan kamu yang mulai, harus tunggu siapa lagi? ",
            R.drawable.edu_detail_5_1,
            R.drawable.edu_detail_5_2,
        ),
        FifthEducationData(
            5,
            "Dampak Sampah Residu",
            "Dampak dari pencemaran lingkungan yang disebabkan oleh sampah anorganik dapat merusak ekosistem alami. Ini dapat mempengaruhi keberlanjutan sumber daya alam, merusak habitat satwa liar, dan mengancam keberlangsungan spesies tertentu.",
            "Peningkatan jumlah sampah anorganik dapat mengganggu keseimbangan ekosistem, baik secara langsung maupun tidak langsung.",
            "Dengan memahami dampak dari sampah anorganik, penting untuk mengambil langkah-langkah untuk mengurangi penggunaannya, mendaur ulang sebanyak mungkin, dan mengelola dengan bijaksana agar dapat menjaga kelestarian lingkungan dan kesehatan manusia.",
            "Jadi, apakah kamu siap menjadi bagian dari agen perubahan bumi yang lebih baik? ",
            "Mulailah memilah sampah dengan baik pada tempatnya dan membangun kesadaran diri dari sekarang. Kalau bukan kamu yang mulai, harus tunggu siapa lagi? ",
            R.drawable.edu_detail_5_1,
            R.drawable.edu_detail_5_2,
        ),
    )

    fun fourthEduList() : List<FourthEducationData> = listOf(
        FourthEducationData(
            1,
            "Contoh Sampah Anorganik",
            "Botol Plastik",
            "Kaca",
            "Karet",
            "Tekstil",
            "Logam",
            "Apakah barang-barang tersebut ada di rumah kamu atau lingkungan kamu? Apa jadinya jika sampah-sampah tersebut di buang sembarangan atau tidak dikelola dengan baik? Yuk, simak dampak dari sampah anorganik!",
            R.drawable.edu_detail_4_1,
            R.drawable.edu_detail_4_2,
            R.drawable.edu_detail_4_3,
            R.drawable.edu_detail_4_4,
            R.drawable.edu_detail_4_5,
        ),
        FourthEducationData(
            2,
            "Contoh Sampah Organik",
            "Botol Plastik",
            "Kaca",
            "Karet",
            "Tekstil",
            "Logam",
            "Apakah barang-barang tersebut ada di rumah kamu atau lingkungan kamu? Apa jadinya jika sampah-sampah tersebut di buang sembarangan atau tidak dikelola dengan baik? Yuk, simak dampak dari sampah anorganik!",
            R.drawable.edu_detail_4_1,
            R.drawable.edu_detail_4_2,
            R.drawable.edu_detail_4_3,
            R.drawable.edu_detail_4_4,
            R.drawable.edu_detail_4_5,
        ),
        FourthEducationData(
            3,
            "Contoh Sampah B3",
            "Botol Plastik",
            "Kaca",
            "Karet",
            "Tekstil",
            "Logam",
            "Apakah barang-barang tersebut ada di rumah kamu atau lingkungan kamu? Apa jadinya jika sampah-sampah tersebut di buang sembarangan atau tidak dikelola dengan baik? Yuk, simak dampak dari sampah anorganik!",
            R.drawable.edu_detail_4_1,
            R.drawable.edu_detail_4_2,
            R.drawable.edu_detail_4_3,
            R.drawable.edu_detail_4_4,
            R.drawable.edu_detail_4_5,
        ),
        FourthEducationData(
            4,
            "Contoh Sampah Kertas",
            "Botol Plastik",
            "Kaca",
            "Karet",
            "Tekstil",
            "Logam",
            "Apakah barang-barang tersebut ada di rumah kamu atau lingkungan kamu? Apa jadinya jika sampah-sampah tersebut di buang sembarangan atau tidak dikelola dengan baik? Yuk, simak dampak dari sampah anorganik!",
            R.drawable.edu_detail_4_1,
            R.drawable.edu_detail_4_2,
            R.drawable.edu_detail_4_3,
            R.drawable.edu_detail_4_4,
            R.drawable.edu_detail_4_5,
        ),
        FourthEducationData(
            5,
            "Contoh Sampah Residu",
            "Botol Plastik",
            "Kaca",
            "Karet",
            "Tekstil",
            "Logam",
            "Apakah barang-barang tersebut ada di rumah kamu atau lingkungan kamu? Apa jadinya jika sampah-sampah tersebut di buang sembarangan atau tidak dikelola dengan baik? Yuk, simak dampak dari sampah anorganik!",
            R.drawable.edu_detail_4_1,
            R.drawable.edu_detail_4_2,
            R.drawable.edu_detail_4_3,
            R.drawable.edu_detail_4_4,
            R.drawable.edu_detail_4_5,
        ),
    )

    fun thirdEduList(): List<ThirdEducationData> = listOf(
        ThirdEducationData(
            id = 1,
            "Ciri-Ciri Sampah Anorganik",
            "Tidak Mudah Terurai",
            "Mengandung Bahan Kimia Berbahaya",
            "Berat dan Padat",
            "Tidak Memiliki Kandungan Organik",
            fifthDesc = "Dan masih banyak lagi ciri-ciri dari sampah anorganik. Apakah kamu sudah mengenali sampah anorganik dengan jelas? Jika belum, nih, aku kasih kamu contoh-contohnya lagi! Ayo lanjutkan perjalanan belajarmu, sudah hampir di penghujung! Semangat!",
            R.drawable.edu_detail_3_1,
            R.drawable.edu_detail_3_2,
            R.drawable.edu_detail_3_3,
            R.drawable.edu_detail_3_4,
        ),
        ThirdEducationData(
            id = 2,
            "Ciri-Ciri Sampah Organik",
            "Tidak Mudah Terurai",
            "Mengandung Bahan Kimia Berbahaya",
            "Berat dan Padat",
            "Tidak Memiliki Kandungan Organik",
            fifthDesc = "Dan masih banyak lagi ciri-ciri dari sampah anorganik. Apakah kamu sudah mengenali sampah anorganik dengan jelas? Jika belum, nih, aku kasih kamu contoh-contohnya lagi! Ayo lanjutkan perjalanan belajarmu, sudah hampir di penghujung! Semangat!",
            R.drawable.edu_detail_3_1,
            R.drawable.edu_detail_3_2,
            R.drawable.edu_detail_3_3,
            R.drawable.edu_detail_3_4,
        ),
        ThirdEducationData(
            id = 3,
            "Ciri-Ciri Sampah B3",
            "Tidak Mudah Terurai",
            "Mengandung Bahan Kimia Berbahaya",
            "Berat dan Padat",
            "Tidak Memiliki Kandungan Organik",
            fifthDesc = "Dan masih banyak lagi ciri-ciri dari sampah anorganik. Apakah kamu sudah mengenali sampah anorganik dengan jelas? Jika belum, nih, aku kasih kamu contoh-contohnya lagi! Ayo lanjutkan perjalanan belajarmu, sudah hampir di penghujung! Semangat!",
            R.drawable.edu_detail_3_1,
            R.drawable.edu_detail_3_2,
            R.drawable.edu_detail_3_3,
            R.drawable.edu_detail_3_4,
        ),
        ThirdEducationData(
            id = 4,
            "Ciri-Ciri Sampah Kertas",
            "Tidak Mudah Terurai",
            "Mengandung Bahan Kimia Berbahaya",
            "Berat dan Padat",
            "Tidak Memiliki Kandungan Organik",
            fifthDesc = "Dan masih banyak lagi ciri-ciri dari sampah anorganik. Apakah kamu sudah mengenali sampah anorganik dengan jelas? Jika belum, nih, aku kasih kamu contoh-contohnya lagi! Ayo lanjutkan perjalanan belajarmu, sudah hampir di penghujung! Semangat!",
            R.drawable.edu_detail_3_1,
            R.drawable.edu_detail_3_2,
            R.drawable.edu_detail_3_3,
            R.drawable.edu_detail_3_4,
        ),
        ThirdEducationData(
            id = 5,
            "Ciri-Ciri Sampah Residu",
            "Tidak Mudah Terurai",
            "Mengandung Bahan Kimia Berbahaya",
            "Berat dan Padat",
            "Tidak Memiliki Kandungan Organik",
            fifthDesc = "Dan masih banyak lagi ciri-ciri dari sampah anorganik. Apakah kamu sudah mengenali sampah anorganik dengan jelas? Jika belum, nih, aku kasih kamu contoh-contohnya lagi! Ayo lanjutkan perjalanan belajarmu, sudah hampir di penghujung! Semangat!",
            R.drawable.edu_detail_3_1,
            R.drawable.edu_detail_3_2,
            R.drawable.edu_detail_3_3,
            R.drawable.edu_detail_3_4,
        ),
    )

    fun secondEduList(): List<SecondEducationData> = listOf(
        SecondEducationData(
            1,
            "Apa saja jenis sampah Anorganik?",
            desc = "Apakah teman-teman tau, sampah anorganik memiliki 2 jenis sampah berdasarkan sifatnya.",
            secondDesc = "Nah, baru tahu, bukan? Yuk, simak penjelasannya!",
            thirdDesc = "Berbicara tentang sulitnya sampah anorganik terurai, kira-kira apa lagi ya ciri-ciri dari sampah anorganik itu? Tanda next ada di sebelah kanan kamu!",
            firstImage = R.drawable.edu_detail_2_1,
            secondImage = R.drawable.edu_detail_2_3,
            thirdImage = R.drawable.edu_detail_2_3
        ),
        SecondEducationData(
            2,
            "Apa saja jenis sampah Organik?",
            desc = "Apakah teman-teman tau, sampah anorganik memiliki 2 jenis sampah berdasarkan sifatnya.",
            secondDesc = "Nah, baru tahu, bukan? Yuk, simak penjelasannya!",
            thirdDesc = "Berbicara tentang sulitnya sampah anorganik terurai, kira-kira apa lagi ya ciri-ciri dari sampah anorganik itu? Tanda next ada di sebelah kanan kamu!",
            firstImage = R.drawable.edu_detail_2_1,
            secondImage = R.drawable.edu_detail_2_3,
            thirdImage = R.drawable.edu_detail_2_3
        ),
        SecondEducationData(
            3,
            "Apa saja jenis sampah Anorganik?",
            desc = "Apakah teman-teman tau, sampah anorganik memiliki 2 jenis sampah berdasarkan sifatnya.",
            secondDesc = "Nah, baru tahu, bukan? Yuk, simak penjelasannya!",
            thirdDesc = "Berbicara tentang sulitnya sampah anorganik terurai, kira-kira apa lagi ya ciri-ciri dari sampah anorganik itu? Tanda next ada di sebelah kanan kamu!",
            firstImage = R.drawable.edu_detail_2_1,
            secondImage = R.drawable.edu_detail_2_3,
            thirdImage = R.drawable.edu_detail_2_3
        ),
        SecondEducationData(
            4,
            "Apa saja jenis sampah Anorganik?",
            desc = "Apakah teman-teman tau, sampah anorganik memiliki 2 jenis sampah berdasarkan sifatnya.",
            secondDesc = "Nah, baru tahu, bukan? Yuk, simak penjelasannya!",
            thirdDesc = "Berbicara tentang sulitnya sampah anorganik terurai, kira-kira apa lagi ya ciri-ciri dari sampah anorganik itu? Tanda next ada di sebelah kanan kamu!",
            firstImage = R.drawable.edu_detail_2_1,
            secondImage = R.drawable.edu_detail_2_3,
            thirdImage = R.drawable.edu_detail_2_3
        ),
        SecondEducationData(
            5,
            "Apa saja jenis sampah Anorganik?",
            desc = "Apakah teman-teman tau, sampah anorganik memiliki 2 jenis sampah berdasarkan sifatnya.",
            secondDesc = "Nah, baru tahu, bukan? Yuk, simak penjelasannya!",
            thirdDesc = "Berbicara tentang sulitnya sampah anorganik terurai, kira-kira apa lagi ya ciri-ciri dari sampah anorganik itu? Tanda next ada di sebelah kanan kamu!",
            firstImage = R.drawable.edu_detail_2_1,
            secondImage = R.drawable.edu_detail_2_3,
            thirdImage = R.drawable.edu_detail_2_3
        ),
    )

    fun firstEduList(): List<FirstEducationData> = listOf(
        FirstEducationData(
            1,
            "Apa itu Sampah Anorganik?",
            desc = "Sampah anorganik adalah jenis sampah yang tidak mudah terurai secara alami oleh mikroorganisme atau proses alamiah. ",
            secondDesc = "Adakah sampah anorganik di sekitarmu? Yuk, coba lihat di sekitar kamu! Dan, jangan lupa untuk di kumpulkan, ya!",
            thirdDesc = "Setelah mengetahui pengertian dari sampah anorganik. Apa saja sih jenis-jenis sampah anorganik itu? Ayo, lanjutkan pembelajaranmu!",
            firstImage = R.drawable.edu_detail_1,
            secondImage = R.drawable.edu_detail_2,
        ),
        FirstEducationData(
            2,
            "Apa itu Sampah Organik?",
            desc = "Sampah Organik adaalaaaahhhh",
            secondDesc = null,
            thirdDesc = null,
            firstImage = R.drawable.edu_detail_1,
            secondImage = null,
        ),
        FirstEducationData(
            3,
            "Apa itu Sampah B3?",
            desc = "Sampah B3 adaalaaaahhhh",
            secondDesc = null,
            thirdDesc = null,
            firstImage = R.drawable.edu_detail_1,
            secondImage = null,
        ),
        FirstEducationData(
            4,
            "Apa itu Sampah Kertas?",
            desc = "Sampah Kertas adaalaaaahhhh",
            secondDesc = null,
            thirdDesc = null,
            firstImage = R.drawable.edu_detail_1,
            secondImage = null,
        ),
        FirstEducationData(
            5,
            "Apa itu Sampah Residu?",
            desc = "Sampah Residu adaalaaaahhhh",
            secondDesc = null,
            thirdDesc = null,
            firstImage = R.drawable.edu_detail_1,
            secondImage = null,
        ),
    )
    fun education(): List<EducationData> = listOf(
        EducationData(
            1,
            "Sampah Anorganik",
            R.drawable.education1
        ),
        EducationData(
            2,
            "Sampah Organik",
            R.drawable.education2
        ),
        EducationData(
            3,
            "Sampah B3",
            R.drawable.education3
        ),
        EducationData(
            4,
            "Sampah Kertas",
            R.drawable.education4
        ),
        EducationData(
            5,
            "Sampah Residu",
            R.drawable.education5
        ),
        )


    fun recycleCategory() : List<RecycleCategoryData> = listOf(
        RecycleCategoryData(
            1,
            "Kertas",
            R.drawable.recycle_1,
            contentId = listOf(
                1,2,3,4,
            )
        ),
        RecycleCategoryData(
            2,
            "Logam",
            R.drawable.recycle_2,
            listOf(
                5,2,3,4
            )
        ),
        RecycleCategoryData(
            3,
            "Tekstil",
            R.drawable.recycle_3,
            listOf(
                5,6,3,4
            )
        ),
        RecycleCategoryData(
            4,
            "Karet",
            R.drawable.recycle_4,
            listOf(
                5,6,7,8
            )
        ),
        RecycleCategoryData(
            5,
            "Karton",
            R.drawable.recycle_5,
            listOf(
                5,6,7,8
            )
        ),
        RecycleCategoryData(
            6,
            "Kulit",
            R.drawable.recycle_6,
            listOf(
                5,6,7,8
            )
        ),
        RecycleCategoryData(
            7,
            "Plastik",
            R.drawable.recycle_7,
            listOf(
                5,6,7,8
            )
        ),
        RecycleCategoryData(
            8,
            "Kaca",
            R.drawable.recycle_8,
            listOf(
                5,6,7,8
            )
        ),
        RecycleCategoryData(
            9,
            "Keramik",
            R.drawable.recycle_9,
            listOf(
                5,6,7,8
            )
        ),
        RecycleCategoryData(
            10,
            "Elektronik",
            R.drawable.recycle_10,
            listOf(
                5,6,7,8
            )
        ),
        RecycleCategoryData(
            11,
            "Minyak",
            R.drawable.recycle_11,
            listOf(
                5,6,7,8
            )
        ),
        RecycleCategoryData(
            12,
            "Baterai",
            R.drawable.recycle_12,
            listOf(
                5,6,7,8
            )
        ),
    )

    fun categoryContentList(): List<FirstRecycleData> = listOf(
        FirstRecycleData(
            1,
            "Origami",
            "Updated 20 Apr",
            R.drawable.paper_1
        ),
        FirstRecycleData(
            2,
            "Kotak Penyimpanan",
            "Updated 19 Apr",
            R.drawable.paper_2
        ),
        FirstRecycleData(
            3,
            "Buku Catatan",
            "Updated 17 Apr",
            R.drawable.paper_3
        ),
        FirstRecycleData(
            4,
            "Kartu Ucapan",
            "Updated 15 Apr",
            R.drawable.paper_4
        ),
        FirstRecycleData(
            5,
            "Logam",
            "Updated 15 Apr",
            R.drawable.paper_4
        ),
        FirstRecycleData(
            6,
            "Logam",
            "Updated 15 Apr",
            R.drawable.paper_4
        ),
        FirstRecycleData(
            7,
            "Logam",
            "Updated 15 Apr",
            R.drawable.paper_4
        ),
        FirstRecycleData(
            8,
            "Logam",
            "Updated 15 Apr",
            R.drawable.paper_4
        ),
    )

    fun secondRecycleList(id : Int): SecondRecycleData{
        return when (id){
            1 -> SecondRecycleData.Content(
                1,
                "Langkah-langkah Membuat Origami dari Kertas Daur Ulang",
                R.drawable.origami_1,
                steps = listOf(
                    Step(
                        1,
                        "Langkah 1: Persiapkan Bahan",
                        "Ambil selembar kertas persegi dari bahan daur ulang yang kamu miliki. Pastikan kertas tersebut cukup besar dan memiliki ketebalan yang sesuai untuk memudahkan proses melipat.",
                        R.drawable.origami_2,
                    ),
                    Step(
                        2,
                        "Langkah 2: Mulai Lipat",
                        "Letakkan kertas di depan kamu dengan sisi berwarna (jika ada) menghadap ke bawah.\n" +
                                "Lipat kertas menjadi dua secara diagonal untuk membentuk segitiga. Pastikan lipatan tersebut rapi dan tepat.",
                        R.drawable.origami_3,
                    ),
                    Step(
                        3,
                        "Langkah 3: Bentuk Segitiga",
                        "Buka kembali lipatan yang baru saja kamu buat, sehingga kembali membentuk persegi panjang.\n" +
                                "Lipat kertas menjadi dua secara diagonal lagi, tetapi kali ini lipatlah ke arah yang berlawanan dari lipatan sebelumnya.",
                        R.drawable.origami_4,
                    ),
                    Step(
                        4,
                        "Langkah 4: Lipat Ujung",
                        "Lipat salah satu ujung segitiga ke titik tengah lipatan sebelumnya. Pastikan lipatan tersebut rapi dan lurus.\n" +
                                "Lakukan langkah yang sama dengan ujung segitiga yang lain, sehingga keduanya bertemu di titik tengah.",
                        R.drawable.origami_5,
                    ),
                    Step(
                        5,
                        "Langkah 5: Bentuk Kepala & Ekor",
                        "Lipat ujung atas segitiga ke bawah sedikit untuk membentuk kepala.\n" +
                                "Lipat ujung bawah segitiga ke atas sedikit untuk membentuk ekor dan origami kamu sudah selesai.",
                        R.drawable.origami_6,
                    ),
                ),
                "Source: liputan6.com/hot/read/4292405/6-cara-melipat-kertas-origami-berbagai-bentuk-yang-mudah-dilakukan?page=3",
            )
            2 -> SecondRecycleData.Content(
                2,
                "Langkah-langkah Membuat Kotak Penyimpanan dari Daur Ulang",
                R.drawable.origami_1,
                steps = listOf(
                    Step(
                        1,
                        "Langkah 1: Persiapkan Bahan",
                        "Ambil selembar kertas persegi dari bahan daur ulang yang kamu miliki. Pastikan kertas tersebut cukup besar dan memiliki ketebalan yang sesuai untuk memudahkan proses melipat.",
                        R.drawable.origami_2,
                    ),
                    Step(
                        2,
                        "Langkah 2: Mulai Lipat",
                        "Letakkan kertas di depan kamu dengan sisi berwarna (jika ada) menghadap ke bawah.\n" +
                                "Lipat kertas menjadi dua secara diagonal untuk membentuk segitiga. Pastikan lipatan tersebut rapi dan tepat.",
                        R.drawable.origami_3,
                    ),
                    Step(
                        3,
                        "Langkah 3: Bentuk Segitiga",
                        "Buka kembali lipatan yang baru saja kamu buat, sehingga kembali membentuk persegi panjang.\n" +
                                "Lipat kertas menjadi dua secara diagonal lagi, tetapi kali ini lipatlah ke arah yang berlawanan dari lipatan sebelumnya.",
                        R.drawable.origami_4,
                    ),
                    Step(
                        4,
                        "Langkah 4: Lipat Ujung",
                        "Lipat salah satu ujung segitiga ke titik tengah lipatan sebelumnya. Pastikan lipatan tersebut rapi dan lurus.\n" +
                                "Lakukan langkah yang sama dengan ujung segitiga yang lain, sehingga keduanya bertemu di titik tengah.",
                        R.drawable.origami_5,
                    ),
                    Step(
                        5,
                        "Langkah 5: Bentuk Kepala & Ekor",
                        "Lipat ujung atas segitiga ke bawah sedikit untuk membentuk kepala.\n" +
                                "Lipat ujung bawah segitiga ke atas sedikit untuk membentuk ekor dan origami kamu sudah selesai.",
                        R.drawable.origami_6,
                    ),
                ),
                "Source: liputan6.com/hot/read/4292405/6-cara-melipat-kertas-origami-berbagai-bentuk-yang-mudah-dilakukan?page=3",
            )
            3 -> SecondRecycleData.Content(
                3,
                "Langkah-langkah Membuat Buku Catatan dari Daur Ulang",
                R.drawable.origami_1,
                steps = listOf(
                    Step(
                        1,
                        "Langkah 1: Persiapkan Bahan",
                        "Ambil selembar kertas persegi dari bahan daur ulang yang kamu miliki. Pastikan kertas tersebut cukup besar dan memiliki ketebalan yang sesuai untuk memudahkan proses melipat.",
                        R.drawable.origami_2,
                    ),
                    Step(
                        2,
                        "Langkah 2: Mulai Lipat",
                        "Letakkan kertas di depan kamu dengan sisi berwarna (jika ada) menghadap ke bawah.\n" +
                                "Lipat kertas menjadi dua secara diagonal untuk membentuk segitiga. Pastikan lipatan tersebut rapi dan tepat.",
                        R.drawable.origami_3,
                    ),
                    Step(
                        3,
                        "Langkah 3: Bentuk Segitiga",
                        "Buka kembali lipatan yang baru saja kamu buat, sehingga kembali membentuk persegi panjang.\n" +
                                "Lipat kertas menjadi dua secara diagonal lagi, tetapi kali ini lipatlah ke arah yang berlawanan dari lipatan sebelumnya.",
                        R.drawable.origami_4,
                    ),
                    Step(
                        4,
                        "Langkah 4: Lipat Ujung",
                        "Lipat salah satu ujung segitiga ke titik tengah lipatan sebelumnya. Pastikan lipatan tersebut rapi dan lurus.\n" +
                                "Lakukan langkah yang sama dengan ujung segitiga yang lain, sehingga keduanya bertemu di titik tengah.",
                        R.drawable.origami_5,
                    ),
                    Step(
                        5,
                        "Langkah 5: Bentuk Kepala & Ekor",
                        "Lipat ujung atas segitiga ke bawah sedikit untuk membentuk kepala.\n" +
                                "Lipat ujung bawah segitiga ke atas sedikit untuk membentuk ekor dan origami kamu sudah selesai.",
                        R.drawable.origami_6,
                    ),
                ),
                "Source: liputan6.com/hot/read/4292405/6-cara-melipat-kertas-origami-berbagai-bentuk-yang-mudah-dilakukan?page=3",
            )
            4 -> SecondRecycleData.Content(
                4,
                "Langkah-langkah Membuat Kartu Ucapan dari Daur Ulang",
                R.drawable.origami_1,
                steps = listOf(
                    Step(
                        1,
                        "Langkah 1: Persiapkan Bahan",
                        "Ambil selembar kertas persegi dari bahan daur ulang yang kamu miliki. Pastikan kertas tersebut cukup besar dan memiliki ketebalan yang sesuai untuk memudahkan proses melipat.",
                        R.drawable.origami_2,
                    ),
                    Step(
                        2,
                        "Langkah 2: Mulai Lipat",
                        "Letakkan kertas di depan kamu dengan sisi berwarna (jika ada) menghadap ke bawah.\n" +
                                "Lipat kertas menjadi dua secara diagonal untuk membentuk segitiga. Pastikan lipatan tersebut rapi dan tepat.",
                        R.drawable.origami_3,
                    ),
                    Step(
                        3,
                        "Langkah 3: Bentuk Segitiga",
                        "Buka kembali lipatan yang baru saja kamu buat, sehingga kembali membentuk persegi panjang.\n" +
                                "Lipat kertas menjadi dua secara diagonal lagi, tetapi kali ini lipatlah ke arah yang berlawanan dari lipatan sebelumnya.",
                        R.drawable.origami_4,
                    ),
                    Step(
                        4,
                        "Langkah 4: Lipat Ujung",
                        "Lipat salah satu ujung segitiga ke titik tengah lipatan sebelumnya. Pastikan lipatan tersebut rapi dan lurus.\n" +
                                "Lakukan langkah yang sama dengan ujung segitiga yang lain, sehingga keduanya bertemu di titik tengah.",
                        R.drawable.origami_5,
                    ),
                    Step(
                        5,
                        "Langkah 5: Bentuk Kepala & Ekor",
                        "Lipat ujung atas segitiga ke bawah sedikit untuk membentuk kepala.\n" +
                                "Lipat ujung bawah segitiga ke atas sedikit untuk membentuk ekor dan origami kamu sudah selesai.",
                        R.drawable.origami_6,
                    ),
                ),
                "Source: liputan6.com/hot/read/4292405/6-cara-melipat-kertas-origami-berbagai-bentuk-yang-mudah-dilakukan?page=3",
            )
            else -> throw IllegalArgumentException("Unknown type: $id")
        }
    }

}
