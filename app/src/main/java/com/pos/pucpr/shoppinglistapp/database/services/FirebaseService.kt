package com.pos.pucpr.shoppinglistapp.database.services

import com.pos.pucpr.shoppinglistapp.database.entities.ShoppingEntity

interface FirebaseService {
  suspend fun add(shopping: ShoppingEntity)

  suspend fun getAll(): List<ShoppingEntity>

  suspend fun getById(id: String): ShoppingEntity?

  suspend fun deleteById(id: String)

  suspend fun update(shopping: ShoppingEntity)
}