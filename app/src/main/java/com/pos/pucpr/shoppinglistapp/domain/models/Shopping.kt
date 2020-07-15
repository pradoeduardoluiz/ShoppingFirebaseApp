package com.pos.pucpr.shoppinglistapp.domain.models

data class Shopping(
    val id: String?,
    val name: String,
    val amount: Int,
    val brand: String,
    val shelfLife: String
)