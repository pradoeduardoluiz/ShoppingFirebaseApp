package com.pos.pucpr.shoppinglistapp.di

import com.pos.pucpr.shoppinglistapp.repository.ShoppingRepository
import com.pos.pucpr.shoppinglistapp.repository.ShoppingRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module

var repositoryModule: Module = module {
  single<ShoppingRepository> { ShoppingRepositoryImpl(service = get()) }
}