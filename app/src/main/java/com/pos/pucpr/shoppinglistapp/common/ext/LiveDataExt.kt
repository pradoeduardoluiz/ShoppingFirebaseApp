package com.pos.pucpr.shoppinglistapp.common.ext

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle

fun <T> LiveData<out T>.observe(owner: LifecycleOwner, observed: (result: T) -> Unit) {
  removeObservers(owner)
  observe(owner, Observer { observed(it) })
}

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
  liveData.removeObservers(this)
  liveData.observe(this, androidx.lifecycle.Observer { it?.let { action(it) } })
}

fun <T> SavedStateHandle?.observe(
  key: String,
  owner: LifecycleOwner,
  observed: (result: T) -> Unit
) {
  this?.getLiveData<T>(key)?.observe(owner) { observed(it) }
}
