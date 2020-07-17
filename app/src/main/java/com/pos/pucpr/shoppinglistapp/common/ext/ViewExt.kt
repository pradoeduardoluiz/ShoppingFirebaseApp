package com.pos.pucpr.shoppinglistapp.common.ext

import android.content.res.ColorStateList
import android.graphics.drawable.Animatable
import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

fun TextInputEditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
  addTextChangedListener(object : TextWatcher {
    override fun afterTextChanged(s: Editable?) = afterTextChanged(s.toString())
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
  })
}

