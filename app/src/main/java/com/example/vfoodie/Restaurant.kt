package com.example.vfoodie

data class Restaurant(
    val name: String,
    val address: String,
    val rating: Float,
    val imageResId: Int // ID dari drawable untuk gambar restoran
)
