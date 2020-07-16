package com.pos.pucpr.shoppinglistapp.database.services

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.pos.pucpr.shoppinglistapp.database.entities.ShoppingEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class FirebaseServiceImpl(private val database: FirebaseFirestore) : FirebaseService {

  override suspend fun add(shopping: ShoppingEntity): Flow<Unit> {
    val collection = database.collection(SHOPPING_KEY)
    collection.add(shopping).await()
    return flow {
      emit(Unit)
    }
  }

  override suspend fun getAll(): Flow<List<ShoppingEntity>> {
    val snapshot = database.collection(SHOPPING_KEY).get().await()
    val shopping = snapshot.toObjects(ShoppingEntity::class.java)
    return flow {
      emit(shopping)
    }
  }

  override suspend fun getById(id: String): Flow<ShoppingEntity> {
    TODO("Not yet implemented")
  }

  override suspend fun deleteById(id: String) {
    TODO("Not yet implemented")
  }

  override suspend fun update(shopping: ShoppingEntity): Flow<Unit> {
    val collection = database.collection(SHOPPING_KEY)
    collection.document(shopping.id ?: "").set(shopping, SetOptions.merge()).await()
    return flow {
      emit(Unit)
    }
  }

  companion object {
    private const val SHOPPING_KEY = "shopping"
  }
}