package com.pos.pucpr.shoppinglistapp.router

interface Router {
  fun showLogin()
  fun showShoppingList()
  fun showShoppingDetails(id: String?)
  fun back()
  fun navigationUp(): Boolean
  fun isInRootScreen(): Boolean
}
