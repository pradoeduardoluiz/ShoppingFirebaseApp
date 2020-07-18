package com.pos.pucpr.shoppinglistapp.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.pos.pucpr.shoppinglistapp.database.services.FirebaseService
import com.pos.pucpr.shoppinglistapp.database.services.FirebaseServiceImpl
import org.koin.core.module.Module
import org.koin.dsl.module

var databaseModule: Module = module {
  single<FirebaseService> { FirebaseServiceImpl(database = get(), auth = get()) }
  single { FirebaseFirestore.getInstance() }
  single { FirebaseAuth.getInstance() }
}