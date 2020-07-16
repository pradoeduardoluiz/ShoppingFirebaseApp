package com.pos.pucpr.shoppinglistapp.ui.list

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.pos.pucpr.shoppinglistapp.domain.usecases.GetAllShopping

class ShoppingListViewModel(
  private val navController: NavController,
  private val getAllShopping: GetAllShopping
) : ViewModel() {

  fun navigateToDetails() {
    navController.navigate(
      ShoppingListFragmentDirections
        .actionShoppingListFragmentToShoppingDetailsFragment()
    )
  }

}