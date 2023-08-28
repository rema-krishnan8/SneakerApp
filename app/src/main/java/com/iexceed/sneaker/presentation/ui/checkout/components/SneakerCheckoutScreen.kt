package com.iexceed.sneaker.presentation.ui.checkout.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.iexceed.sneaker.presentation.Screen
import com.iexceed.sneaker.presentation.ui.checkout.SneakerCartViewModel

@Composable
fun SneakerCheckoutScreen(
    navController: NavController,
    viewModel: SneakerCartViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val iconSize = 24.dp
    val offsetInPx = LocalDensity.current.run { (iconSize / 2).roundToPx() }
    if (state.sneakerCart.isNotEmpty()) {
        state.sneakerCart.forEach {
            CheckoutScreen(Sneakers = state.sneakerCart, viewModel, {
                navController.navigate(
                    Screen.SneakerListScreen.route
                )
            })
        }
    } else {
        CheckoutScreen(Sneakers = emptyList(), viewModel, {
            navController.navigate(
                Screen.SneakerListScreen.route
            )
        })
    }
}
