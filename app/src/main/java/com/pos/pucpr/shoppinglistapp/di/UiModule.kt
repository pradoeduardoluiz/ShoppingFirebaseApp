package com.pos.pucpr.shoppinglistapp.di

import com.pos.pucpr.shoppinglistapp.ui.details.ShoppingDetailsViewModel
import com.pos.pucpr.shoppinglistapp.ui.list.ShoppingListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

var uiModule: Module = module {
  viewModel { ShoppingListViewModel(getAllShopping = get()) }
  viewModel { ShoppingDetailsViewModel() }
}