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
import com.engineerstech.euphoticlabs.ui.theme.PrimaryColor

@Composable
fun IngredientsSection(
    categories: List<String>,
    selectedCategory: String?,
    onCategorySelect: (String) -> Unit
) {
    // Mapping categories to some sample images (In production, these would come from API or a local map)
    val categoryImageMap = mapOf(
        "Eggs" to "https://img.freepik.com/free-photo/eggs-basket-isolated-white-background_1232-1563.jpg",
        "Chicken" to "https://img.freepik.com/free-photo/raw-chicken-legs-white-background_1232-4112.jpg",
        "Palak" to "https://img.freepik.com/free-photo/fresh-spinach-isolated-white-background_1232-1560.jpg",
        "Raagi" to "https://img.freepik.com/free-photo/ragi-flour-bowl-with-ragi-seeds-isolated-white_466689-72283.jpg",
        "Tofu" to "https://img.freepik.com/free-photo/white-tofu-white-background_1232-1555.jpg",
        "Rajma" to "https://img.freepik.com/free-photo/red-kidney-beans-isolated-white-background_1232-1551.jpg",
        "Cauliflower" to "https://img.freepik.com/free-photo/fresh-cauliflower-isolated-white-background_1232-1557.jpg",
        "Pumpkin" to "https://img.freepik.com/free-photo/pumpkin-isolated-white-background_1232-1554.jpg"
    )

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
                        model = categoryImageMap[category] ?: "https://cdn-icons-png.flaticon.com/512/706/706164.png",
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
