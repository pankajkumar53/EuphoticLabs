package com.engineerstech.euphoticlabs.ui.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.engineerstech.euphoticlabs.R
import com.engineerstech.euphoticlabs.domain.model.DishModel

@Composable
fun RecommendedSection(dishes: List<DishModel>) {
    var isRecommendedSelected by remember { mutableStateOf(true) }

    Column(modifier = Modifier.fillMaxWidth()) {
        AppToggle(
            firstLabel = stringResource(R.string.recommended),
            secondLabel = stringResource(R.string.favourites),
            isFirstSelected = isRecommendedSelected,
            onToggleChange = { isRecommendedSelected = it }
        )

        Spacer(modifier = Modifier.height(8.dp))

        DishSection(
            dishes = dishes,
            layoutType = DishLayoutType.ROW
        )
    }
}
