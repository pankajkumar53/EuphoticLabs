package com.engineerstech.euphoticlabs.domain.repo

import com.engineerstech.euphoticlabs.data.network.ApiResult
import com.engineerstech.euphoticlabs.data.network.DishApiService
import com.engineerstech.euphoticlabs.data.network.makeApiCall
import com.engineerstech.euphoticlabs.domain.model.DishModel

class DishRepository(private val dishApiService: DishApiService) {

    suspend fun getAllDishes(): ApiResult<List<DishModel>> {
        return makeApiCall { dishApiService.getAllDishes() }
    }
}