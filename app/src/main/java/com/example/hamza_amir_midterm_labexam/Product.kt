package com.example.hamza_amir_midterm_labexam

import java.io.Serializable

data class Product(
    val title: String,
    val description: String,
    val price: Double,
    val imageResId: Int
) : Serializable
