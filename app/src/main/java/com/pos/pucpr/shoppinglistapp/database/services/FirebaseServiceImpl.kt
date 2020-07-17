package com.pos.pucpr.shoppinglistapp.database.services

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.pos.pucpr.shoppinglistapp.database.entities.ShoppingEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

class FirebaseServiceImpl(private val database: FirebaseFirestore) : FirebaseService {

  override suspend fun add(shopping: ShoppingEntity): Flow<Unit> {
    val collection = database.collection(SHOPPING_KEY)
    collection.add(shopping).await()
    return flow {
      emit(Unit)
    }
  }

  override suspend fun getAll() = flow<List<ShoppingEntity>> {
    try {
      val snapshot = database.collection(SHOPPING_KEY).get().await()
      val shopping = snapshot.toObjects(ShoppingEntity::class.java)
      emit(shopping)
    } catch (e: Exception) {
      Log.d(TAG, "[getAll]: ")
    }
  }.flowOn(Dispatchers.IO)

  override suspend fun getById(id: String): Flow<ShoppingEntity?> {
    return flow {
      try {
        val collection = database.collection(SHOPPING_KEY)
        val snapshot = collection.document(id ?: "").get().await()
        val shopping = snapshot.toObject(ShoppingEntity::class.java)
        emit(shopping)
      } catch (e: Exception) {
        Log.d(TAG, "[getById]: ")
      }
    }
  }

  override suspend fun deleteById(id: String) {
    TODO("Not yet implemented")
  }

  override suspend fun update(shopping: ShoppingEntity): Flow<Unit> {
    return flow {
      val collection = database.collection(SHOPPING_KEY)
      collection.document(shopping.id ?: "").set(shopping, SetOptions.merge()).await()
      emit(Unit)
    }
  }

  companion object {
    private const val TAG: String = "FirebaseServiceImpl"
    private const val SHOPPING_KEY = "shopping"
  }
}