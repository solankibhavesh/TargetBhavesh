package com.target.bhavesh

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.target.bhavesh.ui.screens.details.views.DetailsScreen
import com.target.bhavesh.ui.screens.home.views.HomeScreen
import com.target.bhavesh.ui.navigation.DETAILS_ROUTE
import com.target.bhavesh.ui.navigation.HOME_ROUTE
import com.target.bhavesh.ui.navigation.NavScreens
import com.target.bhavesh.ui.utils.AppMaterialTheme
import com.target.bhavesh.ui.utils.ToolBar
import com.target.bhavesh.util.Constants.DEALS_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppMaterialTheme {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { ToolBar(navController) },
                    content = {
                        Surface(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(it),
                            color = MaterialTheme.colorScheme.background
                        ) {
                            NavHost(
                                navController = navController,
                                startDestination = NavScreens.HomeScreen.route
                            ) {
                                composable(route = HOME_ROUTE) {
                                    HomeScreen(navController = navController)
                                }

                                composable(route = "$DETAILS_ROUTE/{$DEALS_ID}") {
                                    DetailsScreen()
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}