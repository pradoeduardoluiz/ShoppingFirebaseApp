package com.pos.pucpr.shoppinglistapp.repository

import com.pos.pucpr.shoppinglistapp.database.services.FirebaseService
import com.pos.pucpr.shoppinglistapp.domain.models.Shopping
import com.pos.pucpr.shoppinglistapp.mappers.toEntity
import com.pos.pucpr.shoppinglistapp.mappers.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ShoppingRepositoryImpl(private val service: FirebaseService) : ShoppingRepository {

  override suspend fun add(shopping: Shopping): Flow<Unit> {
    return service.add(shopping = shopping.toEntity())
  }

  override suspend fun getAll(): Flow<List<Shopping>> {
    return service.getAll().map { list -> list.map { it.toModel() } }
  }

  override suspend fun getById(id: String): Shopping? {
    return null
  }

  override suspend fun deleteById(id: String) {
    return service.deleteById(id = id)
  }

  override suspend fun update(shopping: Shopping): Flow<Unit> {
    return service.update(shopping = shopping.toEntity())
  }
}