package com.engineerstech.euphoticlabs.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
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

    var showErrorDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        homeViewModel.getAllDishes()
    }

    LaunchedEffect(allDishesState) {
        if (allDishesState is State.Error) {
            showErrorDialog = true
        }
    }

    if (showErrorDialog) {
        AlertDialog(
            onDismissRequest = { showErrorDialog = false },
            title = { Text(text = stringResource(R.string.network_error_title)) },
            text = { 
                val message = (allDishesState as? State.Error)?.message ?: stringResource(R.string.something_went_wrong)
                Text(text = message) 
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showErrorDialog = false
                        homeViewModel.getAllDishes()
                    }
                ) {
                    Text(text = stringResource(R.string.retry))
                }
            },
            dismissButton = {
                TextButton(onClick = { showErrorDialog = false }) {
                    Text(text = stringResource(R.string.dismiss))
                }
            }
        )
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

            is State.Error -> {
                // Background state during error
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = stringResource(R.string.something_went_wrong), color = Color.Gray)
                }
            }

            else -> {}
        }
    }
}

@Composable
fun HomeContent(
    allDishes: List<DishModel>,
    filteredDishes: List<DishModel>,
    categories: List<CategoryItem>,
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
