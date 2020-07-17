package com.pos.pucpr.shoppinglistapp.database.services

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.pos.pucpr.shoppinglistapp.database.entities.ShoppingEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

class FirebaseServiceImpl(private val database: FirebaseFirestore) : FirebaseService {

  override suspend fun add(shopping: ShoppingEntity) = flow<Unit> {
    try {
      val collection = database.collection(SHOPPING_KEY)
      val document = collection.add(shopping).await()
      val updatedShopping = shopping.copy(id = document.id)
      collection.document(document.id).set(updatedShopping, SetOptions.merge()).await()
      emit(Unit)
    } catch (e: Exception) {
      e.printStackTrace()
    }
  }

  override suspend fun getAll() = flow<List<ShoppingEntity>> {
    try {
      val snapshot = database.collection(SHOPPING_KEY).get().await()
      val shopping = snapshot.toObjects(ShoppingEntity::class.java)
      emit(shopping)
    } catch (e: Exception) {
      e.printStackTrace()
    }
  }.flowOn(Dispatchers.IO)

  override suspend fun getById(id: String): Flow<ShoppingEntity?> {
    return flow {
      try {
        val collection = database.collection(SHOPPING_KEY)
        val snapshot = collection.document(id).get().await()
        val shopping = snapshot.toObject(ShoppingEntity::class.java)
        emit(shopping)
      } catch (e: Exception) {
        e.printStackTrace()
      }
    }
  }

  override suspend fun deleteById(id: String) {
    TODO("Not yet implemented")
  }

  override suspend fun update(shopping: ShoppingEntity) = flow {
    try {
      val collection = database.collection(SHOPPING_KEY)
      collection.document(shopping.id ?: "").set(shopping, SetOptions.merge()).await()
      emit(Unit)
    } catch (e: Exception) {
      e.printStackTrace()
    }
  }

  companion object {
    private const val TAG: String = "FirebaseServiceImpl"
    private const val SHOPPING_KEY = "shopping"
  }
}