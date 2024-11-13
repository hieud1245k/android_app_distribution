package com.hieuminh.app_distribution.di

import com.hieuminh.app_distribution.ui.pages.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

private val viewModelModule = module {
    viewModel { HomeViewModel() }
}

//Add module to allModules for use
val allModules = listOf(
    viewModelModule,
)
