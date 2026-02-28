package com.engineerstech.euphoticlabs.data.network

import com.engineerstech.euphoticlabs.domain.model.DishModel
import retrofit2.Response
import retrofit2.http.GET

interface DishApiService {
    @GET("nosh-assignment")
    suspend fun getAllDishes(): Response<List<DishModel>>
} 