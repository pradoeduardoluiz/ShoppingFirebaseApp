package com.pos.pucpr.shoppinglistapp

import android.app.Application
import com.pos.pucpr.shoppinglistapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ShoppingApp : Application() {

  override fun onCreate() {
    super.onCreate()

    startKoin {
      androidLogger()
      androidContext(this@ShoppingApp)
      modules(
        databaseModule,
        repositoryModule,
        domainModule,
        uiModule,
        appModule
      )
    }
  }

}