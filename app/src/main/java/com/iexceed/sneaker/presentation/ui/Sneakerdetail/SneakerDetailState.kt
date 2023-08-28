package com.iexceed.sneaker.presentation.ui.Sneakerdetail

import com.iexceed.sneaker.domain.model.SneakerDetail

data class SneakerDetailState(
    val isLoading: Boolean = false,
    val Sneaker: SneakerDetail? = null,
    val error: String = ""
)
