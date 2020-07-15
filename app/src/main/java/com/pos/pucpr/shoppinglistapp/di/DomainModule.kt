package com.pos.pucpr.shoppinglistapp.di

import com.pos.pucpr.shoppinglistapp.domain.usecases.DeleteShopping
import com.pos.pucpr.shoppinglistapp.domain.usecases.GetAllShopping
import com.pos.pucpr.shoppinglistapp.domain.usecases.GetShopping
import com.pos.pucpr.shoppinglistapp.domain.usecases.SaveShopping
import org.koin.core.module.Module
import org.koin.dsl.module

var domainModule: Module = module {
  single { GetShopping(repository = get()) }
  single { GetAllShopping(repository = get()) }
  single { SaveShopping(repository = get()) }
  single { DeleteShopping(repository = get()) }
}