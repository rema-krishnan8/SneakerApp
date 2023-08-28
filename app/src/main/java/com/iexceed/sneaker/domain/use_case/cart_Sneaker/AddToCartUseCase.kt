package com.iexceed.sneaker.domain.use_case.cart_Sneaker

import com.iexceed.sneaker.domain.model.SneakerCart
import com.iexceed.sneaker.domain.repository.SneakerCartRepositoryApi
import javax.inject.Inject

class AddToCartUseCase @Inject constructor(
    private val repository: SneakerCartRepositoryApi
)  {
    suspend operator fun invoke(cart: SneakerCart) {
        repository.insert(cart)
    }
}