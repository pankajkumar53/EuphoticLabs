package com.engineerstech.euphoticlabs.ui.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.engineerstech.euphoticlabs.ui.home.MockData
import com.engineerstech.euphoticlabs.ui.theme.PrimaryColor

@Composable
fun IngredientsSection(
    categories: List<String>,
    selectedCategory: String?,
    onCategorySelect: (String) -> Unit
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(categories) { category ->
            val isSelected = selectedCategory == category
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .width(80.dp)
                    .clickable { onCategorySelect(category) }
            ) {
                Surface(
                    shape = CircleShape,
                    border = if (isSelected) BorderStroke(2.dp, PrimaryColor) else null,
                    modifier = Modifier.size(64.dp)
                ) {
                    AsyncImage(
                        model = MockData.categoryImageMap[category] ?: "https://cdn-icons-png.flaticon.com/512/706/706164.png",
                        contentDescription = category,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
                            .background(Color(0xFFF5F5F5)),
                        contentScale = ContentScale.Crop
                    )
                }
                Text(
                    text = category,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 8.dp),
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    color = if (isSelected) PrimaryColor else Color.DarkGray
                )
            }
        }
    }
}
