package com.example.vfoodie

data class MenuItem(
    val name: String,
    val description: String,
    val price: Int,
    val rating: Float,
    val imageResId: Int // Pastikan ada field ini
)
