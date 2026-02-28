package com.engineerstech.euphoticlabs.domain.model

data class DishModel(
    val IngredientCategory: String,
    val Time: String,
    val dishCategory: String,
    val dishId: String,
    val dishName: String,
    val imageUrl: String,
    val isPublished: Boolean,
    val isVeg: Boolean
)