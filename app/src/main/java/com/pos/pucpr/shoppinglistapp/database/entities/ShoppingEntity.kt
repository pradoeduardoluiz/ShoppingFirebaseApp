package com.pos.pucpr.shoppinglistapp.database.entities

data class ShoppingEntity(
  val id: String? = "",
  val name: String = "",
  val amount: Int = 0,
  val brand: String = "",
  val shelfLife: String = ""
)