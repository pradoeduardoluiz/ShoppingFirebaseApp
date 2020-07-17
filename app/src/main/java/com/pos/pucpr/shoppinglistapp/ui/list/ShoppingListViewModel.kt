package com.pos.pucpr.shoppinglistapp.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.pos.pucpr.shoppinglistapp.common.State
import com.pos.pucpr.shoppinglistapp.domain.usecases.DeleteShopping
import com.pos.pucpr.shoppinglistapp.domain.usecases.GetAllShopping
import com.pos.pucpr.shoppinglistapp.mappers.toViewData
import com.pos.pucpr.shoppinglistapp.ui.view_data.ShoppingViewData
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ShoppingListViewModel(
  private val navController: NavController,
  private val getAllShopping: GetAllShopping,
  private val deleteShopping: DeleteShopping
) : ViewModel() {

  private val getAllShoppingState: MutableLiveData<State<Unit>> = MutableLiveData()
  private val deleteShoppingState: MutableLiveData<State<Unit>> = MutableLiveData()
  private val shopping: MutableLiveData<List<ShoppingViewData>> = MutableLiveData()

  val viewState = ShoppingListViewState(
    getAllShoppingState = getAllShoppingState,
    deleteShoppingState = deleteShoppingState,
    shopping = shopping
  )

  fun getAllShopping() {
    viewModelScope.launch {
      getAllShoppingState.postValue(State.loading())
      try {
        getAllShopping.invoke().collect { result ->
          getAllShoppingState.postValue(State.success(Unit))
          shopping.postValue(result.map { it.toViewData() })
        }
      } catch (e: Exception) {
        getAllShoppingState.postValue(State.failed(message = e.message ?: ""))
      }
    }
  }

  fun navigateToDetails(id: String?) {
    navController.navigate(
      ShoppingListFragmentDirections
        .actionShoppingListFragmentToShoppingDetailsFragment(id)
    )
  }

}
