package com.pos.pucpr.shoppinglistapp.repository

import com.pos.pucpr.shoppinglistapp.domain.models.Shopping

interface ShoppingRepository {
  suspend fun add(shopping: Shopping)

  suspend fun getAll(): List<Shopping>

  suspend fun getById(id: String): Shopping?

  suspend fun deleteById(id: String)

  suspend fun update(shopping: Shopping)
}