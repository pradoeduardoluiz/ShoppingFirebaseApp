package com.pos.pucpr.shoppinglistapp.ui.list

import androidx.lifecycle.LiveData
import com.pos.pucpr.shoppinglistapp.common.State
import com.pos.pucpr.shoppinglistapp.ui.view_data.ShoppingViewData

data class ShoppingListViewState(
  val deleteShoppingState: LiveData<State<Unit>>,
  val getAllShoppingState: LiveData<State<Unit>>,
  val shopping: LiveData<List<ShoppingViewData>>
)