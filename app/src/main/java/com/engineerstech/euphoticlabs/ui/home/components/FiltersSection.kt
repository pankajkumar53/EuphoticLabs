package com.engineerstech.euphoticlabs.ui.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FiltersSection() {
    val filters = listOf(
        FilterItem("Sort", Icons.Default.Sort),
        FilterItem("Previously Cooked", Icons.Default.History),
        FilterItem("Quick Recipes", Icons.Default.Timer),
        FilterItem("Cuisines", Icons.Default.RestaurantMenu),
        FilterItem("Diet Type", Icons.Default.FilterList)
    )
    
    LazyRow(
        modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(filters) { filter ->
            FilterChip(
                selected = false,
                onClick = {},
                label = { Text(filter.name, fontSize = 13.sp) },
                leadingIcon = { Icon(filter.icon, contentDescription = null, modifier = Modifier.size(16.dp)) },
                trailingIcon = { Icon(Icons.Default.ArrowDropDown, null, modifier = Modifier.size(18.dp)) },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = Color(0xFFF8F8F8),
                    labelColor = Color.DarkGray,
                    iconColor = Color.Gray
                ),
                border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.5f))
            )
        }
    }
}

data class FilterItem(val name: String, val icon: ImageVector)
