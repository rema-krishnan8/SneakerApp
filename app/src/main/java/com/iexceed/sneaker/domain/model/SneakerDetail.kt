package com.iexceed.sneaker.domain.model

import com.iexceed.sneaker.data.remote.dto.Media

data class SneakerDetail(
    val SneakerId: String,
    val name: String,
    val image: Media,
    val brand: String,
    val year: Int,
    val title: String,
    val price: Int

)
