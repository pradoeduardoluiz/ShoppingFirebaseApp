package com.pos.pucpr.shoppinglistapp.domain.usecases

import com.pos.pucpr.shoppinglistapp.domain.models.Shopping
import com.pos.pucpr.shoppinglistapp.repository.ShoppingRepository

class GetAllShopping(private val repository: ShoppingRepository) {

  suspend fun invoke(): List<Shopping> {
    return repository.getAll()
  }
}