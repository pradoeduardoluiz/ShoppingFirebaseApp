package com.pos.pucpr.shoppinglistapp

import android.app.Application
import com.pos.pucpr.shoppinglistapp.di.databaseModule
import com.pos.pucpr.shoppinglistapp.di.domainModule
import com.pos.pucpr.shoppinglistapp.di.repositoryModule
import com.pos.pucpr.shoppinglistapp.di.uiModule
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
        uiModule
      )
    }
  }

}