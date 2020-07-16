package com.pos.pucpr.shoppinglistapp.domain.usecases

import com.pos.pucpr.shoppinglistapp.domain.models.Shopping
import com.pos.pucpr.shoppinglistapp.repository.ShoppingRepository
import kotlinx.coroutines.flow.Flow

class GetShopping(private val repository: ShoppingRepository) {

  suspend fun invoke(id: String): Flow<Shopping?> {
    return repository.getById(id = id)
  }

}