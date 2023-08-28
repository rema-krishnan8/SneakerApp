package com.iexceed.sneaker.presentation.ui.checkout

import com.iexceed.sneaker.domain.model.SneakerCart

sealed class SneakerCartEvent {
    data class DeleteSneaker(val sneakerCart: SneakerCart) : SneakerCartEvent()
}
