package com.iexceed.sneaker.presentation.ui.Sneakerdetail

import com.iexceed.sneaker.domain.model.SneakerCart

sealed class SneakerAddEvent {
    data class InsertSneaker(val sneakerCart: SneakerCart) : SneakerAddEvent()
}
