package com.engineerstech.euphoticlabs.ui.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.SignalCellularAlt
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.engineerstech.euphoticlabs.R
import com.engineerstech.euphoticlabs.domain.model.DishModel

enum class DishLayoutType { ROW, GRID }

@Composable
fun DishSection(
    dishes: List<DishModel>, layoutType: DishLayoutType
) {
    when (layoutType) {
        DishLayoutType.ROW -> {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(dishes) { dish ->
                    DishItemCard(dish)
                }
            }
        }

        DishLayoutType.GRID -> {
            val configuration = LocalConfiguration.current
            val screenWidth = configuration.screenWidthDp
            val columns = if (screenWidth >= 900) 4 else if (screenWidth >= 600) 3 else 2

            Column(modifier = Modifier.padding(16.dp)) {
                dishes.chunked(columns).forEach { rowDishes ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 20.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        rowDishes.forEach { dish ->
                            Box(modifier = Modifier.weight(1f)) {
                                DishItemCard(dish)
                            }
                        }
                        repeat(columns - rowDishes.size) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DishItemCard(dish: DishModel) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(contentAlignment = Alignment.Center) {
                AsyncImage(
                    model = dish.imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(180.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )


                Icon(
                    imageVector = if (dish.isVeg) Icons.Default.Eco else Icons.Default.Stop,
                    contentDescription = null,
                    tint = if (dish.isVeg) Color(0xFF4CAF50) else Color.Red,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(18.dp)
                        .align(Alignment.TopStart)
                )

                Icon(
                    Icons.Outlined.FavoriteBorder,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(18.dp)
                        .align(Alignment.TopEnd)
                )

                Surface(
                    color = Color.White,
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .offset(y = 10.dp),
                    shadowElevation = 3.dp
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.Star,
                            contentDescription = null,
                            tint = Color(0xFFFFB400),
                            modifier = Modifier.size(12.dp)
                        )
                        Text(
                            text = stringResource(
                                R.string.grid_rating
                            ),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 2.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = dish.dishName,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                maxLines = 2,
                textAlign = TextAlign.Center,
                minLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    Icons.Default.Timer,
                    contentDescription = null,
                    modifier = Modifier.size(12.dp),
                    tint = Color.Gray
                )
                Text(
                    dish.Time,
                    fontSize = 11.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 4.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    Icons.Default.SignalCellularAlt,
                    contentDescription = null,
                    modifier = Modifier.size(12.dp),
                    tint = Color.Gray
                )
                Text(
                    text = stringResource(R.string.easy),
                    fontSize = 11.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        }
    }
}
