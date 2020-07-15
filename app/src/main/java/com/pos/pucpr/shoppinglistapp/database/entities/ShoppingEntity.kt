package com.pos.pucpr.shoppinglistapp.database.entities

data class ShoppingEntity(
  val id: String?,
  val name: String,
  val amount: Int,
  val brand: String,
  val shelfLife: String
)