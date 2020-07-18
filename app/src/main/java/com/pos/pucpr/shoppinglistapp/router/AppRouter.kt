package com.pos.pucpr.shoppinglistapp.router

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.pos.pucpr.shoppinglistapp.R

class AppRouter(
  activity: FragmentActivity
) : Router {

  private val navController: NavController by lazy {
    Navigation.findNavController(activity, R.id.nav_host_fragment)
  }
  private val rootScreens = setOf(R.id.loginFragment, R.id.shoppingListFragment)

  init {
    val appBarConfiguration = AppBarConfiguration.Builder(rootScreens).build()
    if (activity is AppCompatActivity) {
      NavigationUI.setupActionBarWithNavController(activity, navController, appBarConfiguration)
    }
  }

  override fun showLogin() {
    val options = NavOptions.Builder()
      .setLaunchSingleTop(true)
      .setPopUpTo(R.id.loginFragment, false)
      .build()
    navController.navigate(R.id.loginFragment, null, options)
  }

  override fun showShoppingList() {
    navController.navigate(R.id.shoppingListFragment)
  }

  override fun showShoppingDetails(id: String?) {
    val args = id?.let {
      Bundle().apply {
        putString("id", it)
      }
    }
    navController.navigate(R.id.shoppingDetailsFragment, args)
  }

  override fun back() {
    navController.popBackStack()
  }

  override fun navigationUp(): Boolean {
    return navController.navigateUp()
  }

  override fun isInRootScreen(): Boolean {
    return rootScreens.contains(navController.currentDestination?.id)
  }
}
