package com.engineerstech.euphoticlabs.ui.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.FormatListBulleted
import androidx.compose.material.icons.automirrored.outlined.FormatListBulleted
import androidx.compose.material.icons.filled.Headset
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Microwave
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.SoupKitchen
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material.icons.outlined.Headset
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Microwave
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.material.icons.outlined.SoupKitchen
import androidx.compose.material.icons.outlined.Tv
import com.engineerstech.euphoticlabs.ui.utils.SideBarItem

object MockData {
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

    // SideBar NavigationRail Item
    val items = listOf(
        SideBarItem("Home", Icons.Filled.Home, Icons.Outlined.Home),
        SideBarItem("Reheat", Icons.Filled.Microwave, Icons.Outlined.Microwave),
        SideBarItem("Preset",Icons.AutoMirrored.Filled.FormatListBulleted,Icons.AutoMirrored.Outlined.FormatListBulleted),
        SideBarItem("Copilot", Icons.Filled.Restaurant, Icons.Outlined.Restaurant),
        SideBarItem("Flavour", Icons.Filled.SoupKitchen, Icons.Outlined.SoupKitchen)
    )
    val serviceItems = listOf(
        SideBarItem("Care Mode", Icons.Filled.Tv, Icons.Outlined.Tv),
        SideBarItem("Support", Icons.Filled.Headset, Icons.Outlined.Headset)
    )
}
