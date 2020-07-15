package com.pos.pucpr.shoppinglistapp.database.services

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.pos.pucpr.shoppinglistapp.database.entities.ShoppingEntity

class FirebaseServiceImpl(private val database: FirebaseFirestore) : FirebaseService {

  override suspend fun add(shopping: ShoppingEntity) {
    val collection = database.collection(SHOPPING_KEY)
    collection.add(shopping)
  }

  override suspend fun getAll(): List<ShoppingEntity> {
    TODO("Not yet implemented")
  }

  override suspend fun getById(id: String): ShoppingEntity? {
    TODO("Not yet implemented")
  }

  override suspend fun deleteById(id: String) {
    TODO("Not yet implemented")
  }

  override suspend fun update(shopping: ShoppingEntity) {
    val collection = database.collection(SHOPPING_KEY)
    collection.document(shopping.id ?: "").set(shopping, SetOptions.merge())
  }

  companion object {
    private const val SHOPPING_KEY = "shopping"
  }
}