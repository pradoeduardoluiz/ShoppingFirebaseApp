package com.pos.pucpr.shoppinglistapp.domain.usecases

import com.pos.pucpr.shoppinglistapp.repository.ShoppingRepository

class DeleteShopping(private val repository: ShoppingRepository) {

  suspend fun invoke(id: String) {
    repository.deleteById(id = id)
  }

}