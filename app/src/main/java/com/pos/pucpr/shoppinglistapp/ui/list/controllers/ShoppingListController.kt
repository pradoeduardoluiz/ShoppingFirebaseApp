package com.pos.pucpr.shoppinglistapp.ui.list.controllers

import com.airbnb.epoxy.TypedEpoxyController
import com.pos.pucpr.shoppinglistapp.ui.list.holders.shoppingViewHolder
import com.pos.pucpr.shoppinglistapp.ui.view_data.ShoppingViewData

class ShoppingListController : TypedEpoxyController<List<ShoppingViewData>>() {

  private var listener: OnClickListener? = null

  override fun buildModels(data: List<ShoppingViewData>?) {
    data?.forEach {
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
  }

  fun setListener(listener: OnClickListener) {
    this.listener = listener
  }

  interface OnClickListener {
    fun onClickListener(shoppingItem: ShoppingViewData)
    fun onDeleteListener(shoppingItem: ShoppingViewData)
  }

}