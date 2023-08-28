package com.iexceed.sneaker.presentation.ui.checkout

import com.iexceed.sneaker.domain.model.SneakerCart

data class SneakerCartState(

    val sneakerCart: List<SneakerCart> = emptyList(),

    val error: String = ""
)
