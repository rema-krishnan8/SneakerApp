package com.iexceed.sneaker.domain.repository

import com.iexceed.sneaker.domain.model.SneakerCart
import kotlinx.coroutines.flow.Flow

interface SneakerCartRepositoryApi {

    fun getCartSneakers(): Flow<List<SneakerCart>>
    suspend fun insert(cart: SneakerCart)
    suspend fun delete(item: SneakerCart)
}
