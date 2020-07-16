package com.pos.pucpr.shoppinglistapp.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pos.pucpr.shoppinglistapp.common.State
import com.pos.pucpr.shoppinglistapp.domain.usecases.GetShopping
import com.pos.pucpr.shoppinglistapp.domain.usecases.SaveShopping
import com.pos.pucpr.shoppinglistapp.mappers.toModel
import com.pos.pucpr.shoppinglistapp.mappers.toViewData
import com.pos.pucpr.shoppinglistapp.ui.view_data.ShoppingViewData
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class ShoppingDetailsViewModel(
  private val saveShopping: SaveShopping,
  private val getShopping: GetShopping
) : ViewModel() {

  private val saveShoppingState: MutableLiveData<State<Unit>> = MutableLiveData()
  private val getShoppingState: MutableLiveData<State<Unit>> = MutableLiveData()
  private var shopping = ShoppingViewData()

  val viewState = ShoppingDetailsViewState(
    saveShoppingState = saveShoppingState,
    getShoppingState = getShoppingState
  )

  fun saveShopping() {
    viewModelScope.launch {
      saveShoppingState.postValue(State.loading())
      flow<Unit> {
        saveShopping.invoke(shopping = shopping.toModel()).collect {
          saveShoppingState.postValue(State.success(Unit))
        }
      }.catch {
        saveShoppingState.postValue(State.failed(message = "Error!!"))
      }
    }
  }

  fun getShopping(id: String) {
    viewModelScope.launch {
      getShoppingState.postValue(State.loading())
      flow<Unit> {
        getShopping.invoke(id = id).collect { result ->
          result?.let {
            shopping = it.toViewData()
            getShoppingState.postValue(State.success(Unit))
          }
        }
      }.catch {
        getShoppingState.postValue(State.failed(message = "Error!!"))
      }
    }
  }
}