package com.neirasphere.ecosphere.domain.model

data class User(
    val id: Int,
    val namaDepan: String? = null,
    val namaBelakang: String? = null,
    val email: String,
    val avatar: String? =  null
)