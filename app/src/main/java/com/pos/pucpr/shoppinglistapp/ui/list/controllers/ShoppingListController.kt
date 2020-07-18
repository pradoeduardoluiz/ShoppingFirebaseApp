package com.pos.pucpr.shoppinglistapp.ui.list.controllers

import com.airbnb.epoxy.Typed2EpoxyController
import com.pos.pucpr.shoppinglistapp.ui.list.holders.loadingViewHolder
import com.pos.pucpr.shoppinglistapp.ui.list.holders.shoppingViewHolder
import com.pos.pucpr.shoppinglistapp.ui.view_data.ShoppingViewData

class ShoppingListController : Typed2EpoxyController<List<ShoppingViewData>, Boolean>() {

  private var listener: OnClickListener? = null
  private var shopping: MutableList<ShoppingViewData> = mutableListOf()
  private var isLoading: Boolean = false

  override fun buildModels(data: List<ShoppingViewData>, isLoading: Boolean) {
    data.forEach {
      shoppingViewHolder {
        id(it.id)
        name(it.name)
        amount(it.amount)
        clickListener { _, _, _, _ ->
          listener?.onClickListener(it)
        }
        deleteListener { _, _, _, _ ->
          listener?.onDeleteListener(it)
        }
      }
    }

    if (isLoading) {
      loadingViewHolder { id(LOADING_ID) }
    }
  }

  fun setData(list: List<ShoppingViewData>) {
    this.shopping = list.toMutableList()
    this.isLoading = false
    setData(this.shopping, this.isLoading)
  }

  fun setLoading(isLoading: Boolean) {
    this.isLoading = isLoading
    setData(this.shopping, this.isLoading)
  }

  fun setListener(listener: OnClickListener) {
    this.listener = listener
  }

  interface OnClickListener {
    fun onClickListener(shoppingItem: ShoppingViewData)
    fun onDeleteListener(shoppingItem: ShoppingViewData)
  }

  companion object {
    private const val LOADING_ID = "loading"
  }

}