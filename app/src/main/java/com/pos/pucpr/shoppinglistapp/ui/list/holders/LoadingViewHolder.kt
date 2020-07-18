package com.pos.pucpr.shoppinglistapp.ui.list.holders

import android.view.View
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.pos.pucpr.shoppinglistapp.R

@EpoxyModelClass(layout = R.layout.loading_item)
abstract class LoadingViewHolder : EpoxyModelWithHolder<LoadingHolder>() {
}

class LoadingHolder : EpoxyHolder() {
  override fun bindView(itemView: View) {}
}