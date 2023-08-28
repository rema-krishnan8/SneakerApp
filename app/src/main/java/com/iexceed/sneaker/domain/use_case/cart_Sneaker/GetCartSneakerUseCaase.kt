package com.iexceed.sneaker.domain.use_case.cart_Sneaker

import com.iexceed.sneaker.domain.model.SneakerCart
import com.iexceed.sneaker.domain.repository.SneakerCartRepositoryApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCartSneakerUseCaase @Inject constructor(
    private val repository: SneakerCartRepositoryApi
) {
    operator fun invoke(): Flow<List<SneakerCart>> {
        return repository.getCartSneakers()
    }
}
