package com.neirasphere.ecosphere.ui.screen.recycle

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neirasphere.ecosphere.R
import com.neirasphere.ecosphere.ui.theme.NeutralColorWhite
import com.neirasphere.ecosphere.ui.theme.PrimaryColor

sealed class RecycleCarousel(
    val image: Int,
    val title: String,
    val description: String,
) {
    object First : RecycleCarousel(
        image = R.drawable.paper_0,
        title = "Origami",
        description = "Kata “origami” berasal dari bahasa Jepang, dengan “ori” yang berarti lipat, dan “kami” yang berarti kertas."
    )

    object Second: RecycleCarousel(
        image = R.drawable.paper_2,
        title = "Kotak",
        description = "Kata “origami” berasal dari bahasa Jepang, dengan “ori” yang berarti lipat, dan “kami” yang berarti kertas."
    )

    object Third: RecycleCarousel(
        image = R.drawable.paper_3,
        title = "Kartu",
        description = "Kata “origami” berasal dari bahasa Jepang, dengan “ori” yang berarti lipat, dan “kami” yang berarti kertas."
    )
}

//@Composable
//fun CarouselItem(){
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            //paddingnya ntar dihapus
//            .padding(top = 12.dp)
//            .padding(horizontal = 12.dp)
//            .height(204.dp) // Tinggi total Box, termasuk ruang untuk gambar yang akan menonjol keluar
//    ) {
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(180.dp)
//                .align(Alignment.BottomCenter), // Card ditempatkan di bagian bawah Box
//            shape = RoundedCornerShape(16.dp),
//            colors = CardDefaults.cardColors(containerColor = PrimaryColor)
//        ) {
//            Column(modifier = Modifier
//                .padding(16.dp)
//            ) {
//                Text(
//                    "Card Content",
//                    style = MaterialTheme.typography.labelLarge.copy(color = NeutralColorWhite),
//                    modifier = Modifier
//                        .padding(top = 28.dp)
//                    )
//                Text(
//                    text = "Kata “origami” berasal dari bahasa Jepang, dengan “ori” yang berarti lipat, dan “kami” yang berarti kertas.",
//                    style = MaterialTheme.typography.bodyMedium.copy(color = NeutralColorWhite),
//                    modifier = Modifier
//                        .padding(top = 28.dp)
//                )
//            }
//        }
//
//        Image(
//            painter = painterResource(id = R.drawable.paper_0), // Ganti dengan resource gambar Anda
//            contentDescription = null,
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .size(width = 180.dp, height = 114.dp)
//                .padding(end = 30.dp)
//                .clip(RoundedCornerShape(10.dp))
//                .align(Alignment.TopEnd) // Mengatur posisi gambar di atas tengah Box
////                .offset(y = -50.dp) // Mengatur offset vertikal agar gambar setengah berada di luar card
//        )
//    }
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(200.dp)
//            .padding(top = 50.dp) // Tambahkan padding untuk memberi ruang bagi gambar di atas card
//    ) {
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(150.dp)
//                .align(Alignment.BottomCenter), // Align card di bagian bawah Box
//            shape = RoundedCornerShape(16.dp),
//        ) {
//            Box(modifier = Modifier.padding(16.dp)) {
//                Text("Card Content", fontSize = 18.sp)
//            }
//        }
//
//        Image(
//            painter = painterResource(id = R.drawable.paper_0), // Ganti dengan resource gambar Anda
//            contentDescription = null,
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .size(100.dp)
//                .clip(RoundedCornerShape(50.dp))
//                .align(Alignment.TopCenter) // Align gambar di bagian atas tengah Box
//                .offset(y = -50.dp) // Mengatur offset gambar agar setengah berada di luar card
//        )
//    }
//}