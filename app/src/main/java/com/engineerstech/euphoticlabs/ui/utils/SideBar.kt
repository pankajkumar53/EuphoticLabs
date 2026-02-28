package com.engineerstech.euphoticlabs.ui.utils

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.engineerstech.euphoticlabs.ui.navigation.LocalNavigationProvider
import com.engineerstech.euphoticlabs.ui.navigation.Routes
import com.engineerstech.euphoticlabs.ui.theme.PrimaryColor


@Composable
fun SideBarWithScreen() {
    val navController = LocalNavigationProvider.current

    val items = listOf(
        SideBarItem("Home", Icons.Filled.Home, Icons.Outlined.Home),
        SideBarItem("Reheat", Icons.Filled.History, Icons.Outlined.History),
        SideBarItem("Preset", Icons.Filled.List, Icons.Outlined.List),
        SideBarItem("Copilot", Icons.Filled.Restaurant, Icons.Outlined.Restaurant),
        SideBarItem("Flavour", Icons.Filled.ShoppingBasket, Icons.Outlined.ShoppingBasket),
        SideBarItem("Care Mode", Icons.Filled.MedicalServices, Icons.Outlined.MedicalServices),
        SideBarItem("Support", Icons.Filled.Headset, Icons.Outlined.Headset)
    )

    var selectedIndex by remember { mutableIntStateOf(0) }

    NavigationRail(
        modifier = Modifier
            .fillMaxHeight()
            .width(80.dp),
        containerColor = Color.White,
        header = {
            Spacer(modifier = Modifier.height(20.dp))
        }
    ) {
        items.forEachIndexed { index, item ->
            val isSelected = selectedIndex == index
            NavigationRailItem(
                selected = isSelected,
                onClick = {
                    selectedIndex = index
                    if (index == 0) {
                        navController.navigate(Routes.Home) {
                            launchSingleTop = true
                        }
                    }
                },
                icon = {
                    Icon(
                        if (isSelected) item.selectedIcon else item.unselectedIcon,
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
    }
}

data class SideBarItem(
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)
