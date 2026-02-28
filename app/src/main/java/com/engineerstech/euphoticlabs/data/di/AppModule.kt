package com.engineerstech.euphoticlabs.data.di

import com.engineerstech.euphoticlabs.BuildConfig
import com.engineerstech.euphoticlabs.data.network.DishApiService
import com.engineerstech.euphoticlabs.domain.repo.DishRepository
import com.engineerstech.euphoticlabs.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    // Provide Retrofit instance
    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Provide DishApiService
    single<DishApiService> { get<Retrofit>().create(DishApiService::class.java) }

    // Provide DishRepository
    single { DishRepository(get()) }

    // Provide DishViewModel
    viewModel { HomeViewModel(get()) }
}
