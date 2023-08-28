package com.iexceed.sneaker.data.repository

import com.iexceed.sneaker.data.remote.SneakerCartDao
import com.iexceed.sneaker.domain.model.SneakerCart
import com.iexceed.sneaker.domain.repository.SneakerCartRepositoryApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SneakerCartRepositoryImpl @Inject constructor(private val cartDao: SneakerCartDao) : SneakerCartRepositoryApi {

    override fun getCartSneakers(): Flow<List<SneakerCart>> {
        return cartDao.getCartSneakers()
    }

    override suspend fun insert(cart: SneakerCart) {
        return cartDao.insert(cart)
    }

    override suspend fun delete(item: SneakerCart) {
        return cartDao.delete(item)
    }
}
