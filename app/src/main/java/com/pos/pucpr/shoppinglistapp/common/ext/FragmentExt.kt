package com.pos.pucpr.shoppinglistapp.common.ext

import androidx.fragment.app.Fragment
import com.pos.pucpr.shoppinglistapp.ui.MainActivity

fun Fragment.findAuth() = (activity as MainActivity).auth

fun Fragment.findRouter() = (activity as MainActivity).router