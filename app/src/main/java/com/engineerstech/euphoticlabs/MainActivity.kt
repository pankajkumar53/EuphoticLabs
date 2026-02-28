package com.engineerstech.euphoticlabs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.engineerstech.euphoticlabs.ui.navigation.LocalNavigationProvider
import com.engineerstech.euphoticlabs.ui.navigation.NavGraph
import com.engineerstech.euphoticlabs.ui.navigation.Routes
import com.engineerstech.euphoticlabs.ui.theme.EuphoticLabsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EuphoticLabsTheme {
                val navController = rememberNavController()
                CompositionLocalProvider(value = LocalNavigationProvider provides navController) {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        NavGraph(
                            modifier = Modifier.padding(innerPadding),
                            startDestination = Routes.Home
                        )
                    }
                }
            }
        }
    }
}