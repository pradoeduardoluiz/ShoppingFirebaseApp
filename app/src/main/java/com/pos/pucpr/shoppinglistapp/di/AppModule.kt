package com.pos.pucpr.shoppinglistapp.di

import android.content.Intent
import androidx.fragment.app.FragmentActivity
import com.pos.pucpr.shoppinglistapp.auth.Auth
import com.pos.pucpr.shoppinglistapp.auth.FirebaseSignIn
import com.pos.pucpr.shoppinglistapp.router.AppRouter
import com.pos.pucpr.shoppinglistapp.router.Router
import org.koin.dsl.module

val appModule = module {
  factory { (activity: FragmentActivity) ->
    FirebaseSignIn(activity) as Auth<Int, Intent>
  }

  factory { (activity: FragmentActivity) ->
    AppRouter(activity) as Router
  }
}