package com.iexceed.sneaker.data.repository

import com.iexceed.sneaker.data.remote.SneakerRetrofitApi
import com.iexceed.sneaker.data.remote.dto.SneakerDetailDtoX
import com.iexceed.sneaker.data.remote.dto.SneakerListDtoItem
import com.iexceed.sneaker.domain.repository.SneakerRepositoryApi
import javax.inject.Inject

class SneakerRepositoryImpl @Inject constructor(
    private val api: SneakerRetrofitApi
) : SneakerRepositoryApi {
    override suspend fun getSneakers(): List<SneakerListDtoItem> {
        return api.getSneakers()
    }

    override suspend fun getSneakerDetail(SneakerId: String): SneakerDetailDtoX {
        return api.getSneakerDetailById(SneakerId = SneakerId)
    }
}
