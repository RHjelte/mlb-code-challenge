package com.rubyh.mlbcodechallenge.di

import com.rubyh.mlbcodechallenge.repository.BeersRepository
import com.rubyh.mlbcodechallenge.ui.BeerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { BeersRepository(get(), get()) }
    viewModel { BeerViewModel(get()) }
}