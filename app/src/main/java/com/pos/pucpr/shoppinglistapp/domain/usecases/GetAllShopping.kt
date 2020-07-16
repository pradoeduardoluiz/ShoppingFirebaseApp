package com.pos.pucpr.shoppinglistapp.domain.usecases

import com.pos.pucpr.shoppinglistapp.domain.models.Shopping
import com.pos.pucpr.shoppinglistapp.repository.ShoppingRepository
import kotlinx.coroutines.flow.Flow

class GetAllShopping(private val repository: ShoppingRepository) {

  suspend fun invoke(): Flow<List<Shopping>> {
    return repository.getAll()
  }
}