package com.pos.pucpr.shoppinglistapp.domain.usecases

import com.pos.pucpr.shoppinglistapp.domain.models.Shopping
import com.pos.pucpr.shoppinglistapp.repository.ShoppingRepository

class GetShopping(private val repository: ShoppingRepository) {

  suspend fun invoke(id: String): Shopping? {
    return repository.getById(id = id)
  }

}