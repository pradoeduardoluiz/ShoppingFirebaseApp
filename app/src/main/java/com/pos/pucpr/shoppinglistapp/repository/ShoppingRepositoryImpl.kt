package com.pos.pucpr.shoppinglistapp.repository

import com.pos.pucpr.shoppinglistapp.database.services.FirebaseService
import com.pos.pucpr.shoppinglistapp.domain.models.Shopping
import com.pos.pucpr.shoppinglistapp.mappers.toEntity
import com.pos.pucpr.shoppinglistapp.mappers.toModel

class ShoppingRepositoryImpl(private val service: FirebaseService) : ShoppingRepository {

  override suspend fun add(shopping: Shopping) {
    return service.add(shopping = shopping.toEntity())
  }

  override suspend fun getAll(): List<Shopping> {
    return service.getAll().map { it.toModel() }
  }

  override suspend fun getById(id: String): Shopping? {
    return service.getById(id = id)?.toModel()
  }

  override suspend fun deleteById(id: String) {
    return service.deleteById(id = id)
  }

  override suspend fun update(shopping: Shopping) {
    return service.update(shopping = shopping.toEntity())
  }
}