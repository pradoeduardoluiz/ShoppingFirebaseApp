package com.pos.pucpr.shoppinglistapp.repository

import com.pos.pucpr.shoppinglistapp.domain.models.Shopping
import kotlinx.coroutines.flow.Flow

interface ShoppingRepository {
  suspend fun add(shopping: Shopping): Flow<Unit>

  suspend fun getAll(): Flow<List<Shopping>>

  suspend fun getById(id: String): Flow<Shopping?>

  suspend fun deleteById(id: String)

  suspend fun update(shopping: Shopping): Flow<Unit>
}