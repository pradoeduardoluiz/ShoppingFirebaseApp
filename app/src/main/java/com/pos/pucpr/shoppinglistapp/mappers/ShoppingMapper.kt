package com.pos.pucpr.shoppinglistapp.mappers

import com.pos.pucpr.shoppinglistapp.database.entities.ShoppingEntity
import com.pos.pucpr.shoppinglistapp.domain.models.Shopping

fun ShoppingEntity.toModel() = Shopping(
  id = id,
  name = name,
  amount = amount,
  brand = brand,
  shelfLife = shelfLife
)

fun Shopping.toEntity() = ShoppingEntity(
  id = id,
  name = name,
  amount = amount,
  brand = brand,
  shelfLife = shelfLife
)