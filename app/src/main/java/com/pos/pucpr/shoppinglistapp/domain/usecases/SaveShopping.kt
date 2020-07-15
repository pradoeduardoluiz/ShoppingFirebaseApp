package com.pos.pucpr.shoppinglistapp.domain.usecases

import com.pos.pucpr.shoppinglistapp.domain.models.Shopping
import com.pos.pucpr.shoppinglistapp.repository.ShoppingRepository

class SaveShopping(private val repository: ShoppingRepository) {

  suspend fun invoke(shopping: Shopping) {
    if (shopping.id.isNullOrEmpty()) {
      repository.update(shopping = shopping)
    } else {
      repository.add(shopping = shopping)
    }
  }

}