package com.engineerstech.euphoticlabs.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.engineerstech.euphoticlabs.data.network.ApiResult
import com.engineerstech.euphoticlabs.domain.repo.DishRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.engineerstech.euphoticlabs.data.resource.State
import com.engineerstech.euphoticlabs.domain.model.DishModel
import kotlinx.coroutines.launch

class HomeViewModel(private val dishRepository: DishRepository) : ViewModel() {

    private val _state = MutableStateFlow<State<List<DishModel>>>(State.Idle)
    val state: StateFlow<State<List<DishModel>>> = _state


    fun getAllDishes() {
        viewModelScope.launch {
            _state.value = State.Loading
            when (val result = dishRepository.getAllDishes()) {
                is ApiResult.Success -> {
                    result.data?.let {
                        Log.d("Dishes", "getAllDishes: $it")
                        _state.value = State.Success(it)
                    } ?: run {
                        _state.value = State.Error("Dishes data is null")
                    }
                }
                is ApiResult.Error -> {
                    _state.value = State.Error(result.message ?: "Failed to fetch Dishes")
                }
                else -> {}
            }
        }
    }


}