package com.engineerstech.euphoticlabs.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.engineerstech.euphoticlabs.ui.home.HomeScreen
import com.engineerstech.euphoticlabs.ui.utils.SideBarWithScreen
import kotlinx.serialization.Serializable

val LocalNavigationProvider = staticCompositionLocalOf<NavHostController> {
    error("No NavHost provided")
}

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    startDestination: Routes.Home
) {
    val navHostController = LocalNavigationProvider.current

    NavHost(
        navController = navHostController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable<Routes.Home> {
            Row(modifier = Modifier.fillMaxSize()) {
                SideBarWithScreen()
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                ) {
                    HomeScreen()
                }
            }
        }
    }
}

@Serializable
sealed class Routes {
    @Serializable
    data object Home
}
