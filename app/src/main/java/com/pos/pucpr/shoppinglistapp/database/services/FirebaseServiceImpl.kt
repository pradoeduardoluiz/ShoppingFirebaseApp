package com.pos.pucpr.shoppinglistapp.database.services

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.pos.pucpr.shoppinglistapp.database.entities.ShoppingEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class FirebaseServiceImpl(private val database: FirebaseFirestore, private val auth: FirebaseAuth) :
  FirebaseService {

  override suspend fun add(shopping: ShoppingEntity) = flow<Unit> {
    try {
      val currentUser = auth.currentUser
      currentUser?.let {
        val newShopping = shopping.copy(userId = currentUser.uid)
        val collection = database.collection(SHOPPING_KEY)
        val document = collection.add(newShopping).await()
        val updatedShopping = newShopping.copy(id = document.id, userId = currentUser.uid)
        collection.document(document.id).set(updatedShopping, SetOptions.merge()).await()
        emit(Unit)
      } ?: run {
        throw Exception("Unauthorized used.")
      }
    } catch (e: Exception) {
      e.printStackTrace()
    }
  }

  @ExperimentalCoroutinesApi
  override suspend fun getAll() = callbackFlow<List<ShoppingEntity>> {
    try {
      val currentUser = auth.currentUser
      val subscription = database.collection(SHOPPING_KEY)
        .whereEqualTo(USER_ID_KEY, currentUser?.uid)
        .addSnapshotListener { snapshot, error ->
          if (error != null) {
            return@addSnapshotListener
          }
          if (snapshot != null && !snapshot.isEmpty) {
            val shopping = snapshot.map { document ->
              document.toObject(ShoppingEntity::class.java)
            }
            offer(shopping)
          } else {
            offer(emptyList())
          }
        }
      awaitClose {
        subscription.remove()
      }
    } catch (e: Exception) {
      e.printStackTrace()
    }
  }

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
    val collection = database.collection(SHOPPING_KEY)
    collection.document(id).delete()
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
    private const val USER_ID_KEY = "userId"
    private const val SHOPPING_KEY = "shopping"
  }
}