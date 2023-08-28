package com.iexceed.sneaker.domain.repository

import com.iexceed.sneaker.data.remote.dto.SneakerDetailDtoX
import com.iexceed.sneaker.data.remote.dto.SneakerListDtoItem

interface SneakerRepositoryApi {
    suspend fun getSneakers(): List<SneakerListDtoItem>
    suspend fun getSneakerDetail(SneakerId: String): SneakerDetailDtoX
}