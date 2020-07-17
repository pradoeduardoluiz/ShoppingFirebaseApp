package com.pos.pucpr.shoppinglistapp.ui.details

import androidx.lifecycle.LiveData
import com.pos.pucpr.shoppinglistapp.common.State

data class ShoppingDetailsViewState(
  val isSaveButtonEnabled: LiveData<Boolean>,
  val saveShoppingState: LiveData<State<Unit>>,
  val getShoppingState: LiveData<State<Unit>>
)