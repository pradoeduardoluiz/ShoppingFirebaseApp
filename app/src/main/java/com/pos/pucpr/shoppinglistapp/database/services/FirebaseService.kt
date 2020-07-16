package com.pos.pucpr.shoppinglistapp.database.services

import com.pos.pucpr.shoppinglistapp.database.entities.ShoppingEntity
import kotlinx.coroutines.flow.Flow

interface FirebaseService {
  suspend fun add(shopping: ShoppingEntity): Flow<Unit>

  suspend fun getAll(): Flow<List<ShoppingEntity>>

  suspend fun getById(id: String): Flow<ShoppingEntity>

  suspend fun deleteById(id: String)

  suspend fun update(shopping: ShoppingEntity): Flow<Unit>
}