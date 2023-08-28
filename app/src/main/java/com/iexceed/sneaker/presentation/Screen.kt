package com.iexceed.sneaker.presentation

import com.iexceed.sneaker.R

sealed class Screen(val route: String,var icon: Int) {
    object SneakerListScreen : Screen("Sneaker_list_screen",R.drawable.baseline_home_24)
    object SneakerDetailScreen : Screen("Sneaker_detail_screen",R.drawable.baseline_home_24)
    object SneakerCheckoutScreen : Screen("Sneaker_checkout_screen", R.drawable.baseline_shopping_cart_24)
}
