package com.example.cashregister.model

data class Purchase(
    val productName: String,
    val quantity: Int,
    val totalPrice: Double,
    val purchaseDate: String
)
