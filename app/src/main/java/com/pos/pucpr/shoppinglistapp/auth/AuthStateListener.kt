package com.pos.pucpr.shoppinglistapp.auth

interface AuthStateListener {
    fun onAuthChanged(isLoggedIn: Boolean)
}
