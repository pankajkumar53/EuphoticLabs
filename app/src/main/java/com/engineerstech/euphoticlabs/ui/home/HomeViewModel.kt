package com.engineerstech.euphoticlabs.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.engineerstech.euphoticlabs.data.network.ApiResult
import com.engineerstech.euphoticlabs.domain.repo.DishRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.engineerstech.euphoticlabs.data.resource.State
import com.engineerstech.euphoticlabs.domain.model.DishModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class CategoryItem(val name: String, val imageUrl: String)

class HomeViewModel(private val dishRepository: DishRepository) : ViewModel() {

    private val _allDishesState = MutableStateFlow<State<List<DishModel>>>(State.Idle)
    val allDishesState: StateFlow<State<List<DishModel>>> = _allDishesState

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _isDishToggleSelected = MutableStateFlow(false)
    val isDishToggleSelected: StateFlow<Boolean> = _isDishToggleSelected

    private val _selectedCategory = MutableStateFlow<String?>(null)
    val selectedCategory: StateFlow<String?> = _selectedCategory

    val filteredDishes = combine(
        _allDishesState,
        _searchQuery,
        _selectedCategory
    ) { state, query, category ->
        if (state is State.Success) {
            state.data.filter { dish ->
                val matchesQuery = dish.dishName.contains(query, ignoreCase = true) ||
                        dish.dishCategory.contains(query, ignoreCase = true) ||
                        dish.IngredientCategory.contains(query, ignoreCase = true)
                
                val matchesCategory = category == null || 
                        dish.dishCategory == category || 
                        dish.IngredientCategory == category
                
                matchesQuery && matchesCategory
            }
        } else {
            emptyList()
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val categories = combine(
        _allDishesState,
        _isDishToggleSelected
    ) { state, isDishSelected ->
        if (state is State.Success) {
            val dishes = state.data
            if (isDishSelected) {
                dishes.groupBy { it.dishCategory }.map { (name, list) ->
                    CategoryItem(name, list.firstOrNull()?.imageUrl ?: "")
                }
            } else {
                dishes.groupBy { it.IngredientCategory }.map { (name, list) ->
                    CategoryItem(name, list.firstOrNull()?.imageUrl ?: "")
                }
            }
        } else {
            emptyList()
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun getAllDishes() {
        viewModelScope.launch {
            _allDishesState.value = State.Loading
            when (val result = dishRepository.getAllDishes()) {
                is ApiResult.Success -> {
                    result.data?.let {
                        _allDishesState.value = State.Success(it)
                    } ?: run {
                        _allDishesState.value = State.Error("Dishes data is null")
                    }
                }
                is ApiResult.Error -> {
                    _allDishesState.value = State.Error(result.message ?: "Failed to fetch Dishes")
                }
                else -> {}
            }
        }
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    fun onToggleChange(isDishSelected: Boolean) {
        _isDishToggleSelected.value = isDishSelected
        _selectedCategory.value = null
    }

    fun onCategorySelect(category: String) {
        _selectedCategory.value = if (_selectedCategory.value == category) null else category
    }
}
