package com.pos.pucpr.shoppinglistapp.database.entities

import com.google.firebase.firestore.Exclude

data class UserEntity(
  var uid: String = "",
  var name: String = "",
  var email: String = "",
  @Exclude var isAuthenticated: Boolean = false,
  @Exclude var isNew: Boolean = false,
  @Exclude var isCreated: Boolean = false
)