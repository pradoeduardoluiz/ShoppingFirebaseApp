package com.pos.pucpr.shoppinglistapp.di

import androidx.navigation.NavController
import com.pos.pucpr.shoppinglistapp.ui.details.ShoppingDetailsViewModel
import com.pos.pucpr.shoppinglistapp.ui.list.ShoppingListViewModel
import com.pos.pucpr.shoppinglistapp.ui.list.controllers.ShoppingListController
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

var uiModule: Module = module {
  viewModel { (navController: NavController) ->
    ShoppingListViewModel(
      navController = navController,
      getAllShopping = get(),
      deleteShopping = get()
    )
  }
  viewModel { (navController: NavController) ->
    ShoppingDetailsViewModel(
      navController = navController,
      saveShopping = get(),
      getShopping = get()
    )
  }
  single { ShoppingListController() }
}