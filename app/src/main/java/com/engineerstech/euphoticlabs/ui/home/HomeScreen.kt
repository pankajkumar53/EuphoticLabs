package com.engineerstech.euphoticlabs.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.engineerstech.euphoticlabs.R
import com.engineerstech.euphoticlabs.data.resource.State
import com.engineerstech.euphoticlabs.domain.model.DishModel
import com.engineerstech.euphoticlabs.ui.home.components.*
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen() {
    val homeViewModel: HomeViewModel = koinViewModel()
    val allDishesState by homeViewModel.allDishesState.collectAsState()
    val searchQuery by homeViewModel.searchQuery.collectAsState()
    val isDishToggleSelected by homeViewModel.isDishToggleSelected.collectAsState()
    val selectedCategory by homeViewModel.selectedCategory.collectAsState()
    val filteredDishes by homeViewModel.filteredDishes.collectAsState()
    val categories by homeViewModel.categories.collectAsState()

    LaunchedEffect(Unit) {
        homeViewModel.getAllDishes()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        when (allDishesState) {
            is State.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            is State.Error -> {
                Text(
                    text = (allDishesState as State.Error).message,
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.Red
                )
            }

            is State.Success -> {
                val dishes = (allDishesState as State.Success<List<DishModel>>).data
                HomeContent(
                    allDishes = dishes,
                    filteredDishes = filteredDishes,
                    categories = categories,
                    searchQuery = searchQuery,
                    isDishToggleSelected = isDishToggleSelected,
                    selectedCategory = selectedCategory,
                    onSearchChange = { homeViewModel.onSearchQueryChange(it) },
                    onToggleChange = { homeViewModel.onToggleChange(it) },
                    onCategorySelect = { homeViewModel.onCategorySelect(it) }
                )
            }

            else -> {}
        }
    }
}

@Composable
fun HomeContent(
    allDishes: List<DishModel>,
    filteredDishes: List<DishModel>,
    categories: List<String>,
    searchQuery: String,
    isDishToggleSelected: Boolean,
    selectedCategory: String?,
    onSearchChange: (String) -> Unit,
    onToggleChange: (Boolean) -> Unit,
    onCategorySelect: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        HomeTopBar(
            searchQuery = searchQuery,
            onSearchChange = onSearchChange
        )

        RecommendedSection(allDishes.take(10))

        PreviouslyCookedSection(allDishes.takeLast(5))

        AppToggle(
            firstLabel = stringResource(R.string.dish),
            secondLabel = stringResource(R.string.ingredients),
            isFirstSelected = isDishToggleSelected,
            onToggleChange = onToggleChange
        )

        IngredientsSection(
            categories = categories,
            selectedCategory = selectedCategory,
            onCategorySelect = onCategorySelect
        )

        FiltersSection()

        DishSection(
            dishes = filteredDishes,
            layoutType = DishLayoutType.GRID
        )

        Spacer(modifier = Modifier.height(32.dp))
    }
}
