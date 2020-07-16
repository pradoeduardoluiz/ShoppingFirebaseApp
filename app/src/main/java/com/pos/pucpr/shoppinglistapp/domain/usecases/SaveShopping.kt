package com.pos.pucpr.shoppinglistapp.domain.usecases

import com.pos.pucpr.shoppinglistapp.domain.models.Shopping
import com.pos.pucpr.shoppinglistapp.repository.ShoppingRepository
import kotlinx.coroutines.flow.Flow

class SaveShopping(private val repository: ShoppingRepository) {

  suspend fun invoke(shopping: Shopping): Flow<Unit> {
    return if (shopping.id.isNullOrEmpty()) {
      repository.update(shopping = shopping)
    } else {
      repository.add(shopping = shopping)
    }
  }

}