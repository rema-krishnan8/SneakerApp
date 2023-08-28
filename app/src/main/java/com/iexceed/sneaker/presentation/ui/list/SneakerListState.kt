package com.iexceed.sneaker.presentation.ui.list

import com.iexceed.sneaker.domain.model.Sneaker

data class SneakerListState(
    val isLoading: Boolean = false,
    var Sneakers: List<Sneaker> = emptyList(),
    val error: String = ""
) {
}
