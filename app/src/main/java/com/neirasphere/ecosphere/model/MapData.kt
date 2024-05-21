package com.neirasphere.ecosphere.model

import androidx.annotation.DrawableRes
import com.google.android.gms.maps.model.LatLng

data class MapData(
    val id: Long,
    val latLong: LatLng,
    val title: String,
    val description: String,
    val rating: Double,
    val detailLocation: String,
    @DrawableRes val image: List<Int>,
    val openLocation: List<String>,
    val facilities: List<String>,
    val trashVariantAvailable: List<String>,
)
