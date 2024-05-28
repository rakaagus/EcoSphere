package com.neirasphere.ecosphere.presentation.screen.recycle

import com.neirasphere.ecosphere.R

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