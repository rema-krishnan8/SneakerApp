package com.iexceed.sneaker.data.remote

import com.iexceed.sneaker.data.remote.dto.SneakerDetailDtoX
import com.iexceed.sneaker.data.remote.dto.SneakerListDtoItem
import retrofit2.http.GET
import retrofit2.http.Path

interface SneakerRetrofitApi {
    @GET("Shoes")
    suspend fun getSneakers(): List<SneakerListDtoItem>

    @GET("Shoes/{SneakerId}")
    suspend fun getSneakerDetailById(@Path("SneakerId") SneakerId: String): SneakerDetailDtoX
}
