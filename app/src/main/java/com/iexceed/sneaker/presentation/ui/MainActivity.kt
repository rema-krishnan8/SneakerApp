package com.iexceed.sneaker.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.iexceed.sneaker.presentation.Screen
import com.iexceed.sneaker.presentation.ui.Sneakerdetail.components.SneakerDetailScreen
import com.iexceed.sneaker.presentation.ui.checkout.components.SneakerCheckoutScreen
import com.iexceed.sneaker.presentation.ui.list.components.SneakerListScreen
import com.iexceed.sneaker.ui.theme.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            sneakerTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Navigation(navController = navController)
            }
        },
        backgroundColor = MaterialTheme.colors.background
        // Set background color to avoid the white flashing when you switch between screens
    )
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.SneakerListScreen.route) {
        composable(Screen.SneakerListScreen.route) {
            SneakerListScreen(navController)
        }
        composable(Screen.SneakerDetailScreen.route + "/{SneakerId}") {
            SneakerDetailScreen(navController)
        }
        composable(Screen.SneakerCheckoutScreen.route) {
            SneakerCheckoutScreen(navController)
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        Screen.SneakerListScreen,
        Screen.SneakerCheckoutScreen
    )
    BottomNavigation(
        backgroundColor = white,
        contentColor = colorPrimary,

        elevation = 8.dp,
        modifier = Modifier.clip(RoundedCornerShape(80.dp, 80.dp, 80.dp, 80.dp)).graphicsLayer {
            clip = true
            shape = RoundedCornerShape(topStart = 80.dp, topEnd = 80.dp, bottomEnd = 80.dp, bottomStart = 80.dp)
            shadowElevation = 5.0f
            spotShadowColor = light_gray
        }.height(100.dp)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            val selected = currentRoute == item.route
            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(id = item.icon),
                        "nav icon",
                        tint = if (selected) white else colorPrimary,
                        modifier = Modifier.size(50.dp).background(color = if (selected) colorPrimary else white, shape = CircleShape).clip(
                            CircleShape
                        )
                    )
                },

                selectedContentColor = white,
                unselectedContentColor = colorprimarywhite,
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }

            )
        }
    }
}
