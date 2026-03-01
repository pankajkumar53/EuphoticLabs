package com.engineerstech.euphoticlabs.ui.utils

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.engineerstech.euphoticlabs.ui.home.MockData.items
import com.engineerstech.euphoticlabs.ui.home.MockData.serviceItems
import com.engineerstech.euphoticlabs.ui.navigation.LocalNavigationProvider
import com.engineerstech.euphoticlabs.ui.navigation.Routes
import com.engineerstech.euphoticlabs.ui.theme.PrimaryColor

@Composable
fun SideBarWithScreen() {
    val navController = LocalNavigationProvider.current

    var selectedIndex by remember { mutableIntStateOf(0) }

    NavigationRail(
        modifier = Modifier
            .fillMaxHeight()
            .width(90.dp),
        containerColor = Color.White,
    ) {
        items.forEachIndexed { index, item ->
            SideBarNavigationItem(
                item = item,
                isSelected = selectedIndex == index,
                onClick = {
                    selectedIndex = index
                    if (index == 0) {
                        navController.navigate(Routes.Home) {
                            launchSingleTop = true
                        }
                    }
                }
            )
        }

        HorizontalDivider(modifier = Modifier.padding(20.dp))

        serviceItems.forEachIndexed { index, item ->
            val serviceIndex = items.size + index

            SideBarNavigationItem(
                item = item,
                isSelected = selectedIndex == serviceIndex,
                onClick = {
                    selectedIndex = serviceIndex
                }
            )
        }
    }

}

@Composable
fun SideBarNavigationItem(
    item: SideBarItem,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    NavigationRailItem(
        selected = isSelected,
        onClick = onClick,
        icon = {
            Icon(
                imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                contentDescription = item.label,
                modifier = Modifier.size(24.dp)
            )
        },
        label = {
            Text(
                text = item.label,
                fontSize = 10.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
            )
        },
        alwaysShowLabel = true,
        colors = NavigationRailItemDefaults.colors(
            selectedIconColor = PrimaryColor,
            selectedTextColor = PrimaryColor,
            unselectedIconColor = Color.Gray,
            unselectedTextColor = Color.Gray,
            indicatorColor = Color.Transparent
        )
    )
}

data class SideBarItem(
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)
