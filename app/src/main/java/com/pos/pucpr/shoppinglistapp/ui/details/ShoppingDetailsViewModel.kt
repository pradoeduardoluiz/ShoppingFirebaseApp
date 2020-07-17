package com.pos.pucpr.shoppinglistapp.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.pos.pucpr.shoppinglistapp.common.State
import com.pos.pucpr.shoppinglistapp.domain.usecases.GetShopping
import com.pos.pucpr.shoppinglistapp.domain.usecases.SaveShopping
import com.pos.pucpr.shoppinglistapp.mappers.toModel
import com.pos.pucpr.shoppinglistapp.mappers.toViewData
import com.pos.pucpr.shoppinglistapp.ui.view_data.ShoppingViewData
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ShoppingDetailsViewModel(
  private val navController: NavController,
  private val saveShopping: SaveShopping,
  private val getShopping: GetShopping
) : ViewModel() {

  private val saveShoppingState: MutableLiveData<State<Unit>> = MutableLiveData()
  private val getShoppingState: MutableLiveData<State<Unit>> = MutableLiveData()
  private val isSaveButtonEnabled: MutableLiveData<Boolean> = MutableLiveData()
  private var shopping = ShoppingViewData()

  val viewState = ShoppingDetailsViewState(
    isSaveButtonEnabled = isSaveButtonEnabled,
    saveShoppingState = saveShoppingState,
    getShoppingState = getShoppingState
  )

  fun getShopping() = shopping

  fun saveShopping() {
    viewModelScope.launch {
      saveShoppingState.postValue(State.loading())
      try {
        saveShopping.invoke(shopping = shopping.toModel()).collect {
          saveShoppingState.postValue(State.success(Unit))
          navController.navigateUp()
        }
      } catch (e: Exception) {
        saveShoppingState.postValue(State.failed(message = e.message ?: ""))
      }
    }
  }

  fun getShopping(id: String) {
    viewModelScope.launch {
      getShoppingState.postValue(State.loading())
      try {
        getShopping.invoke(id = id).collect { result ->
          result?.let {
            shopping = it.toViewData()
            checkRequiredFields()
            getShoppingState.postValue(State.success(Unit))
          }
        }
      } catch (e: Exception) {
        getShoppingState.postValue(State.failed(message = e.message ?: ""))
      }
    }
  }

  fun afterNameChanged(name: String) {
    shopping.name = name
    checkRequiredFields()
  }

  fun afterAmountChanged(amount: Int) {
    shopping.amount = amount
    checkRequiredFields()
  }

  fun afterBrandChanged(brand: String) {
    shopping.brand = brand
    checkRequiredFields()
  }

  fun afterShelfLifeChanged(shelfLife: String) {
    shopping.shelfLife = shelfLife
    checkRequiredFields()
  }

  private fun checkRequiredFields() {
    val isAllFieldsRequiredFilled =
      shopping.name.isNotEmpty() && shopping.amount > 0 && shopping.brand.isNotEmpty()
    isSaveButtonEnabled.postValue(isAllFieldsRequiredFilled)
  }
}
