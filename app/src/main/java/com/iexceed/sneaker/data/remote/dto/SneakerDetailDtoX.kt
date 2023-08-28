package com.iexceed.sneaker.data.remote.dto

import com.iexceed.sneaker.domain.model.SneakerDetail

data class SneakerDetailDtoX(
    val brand: String,
    val colorway: String,
    val gender: String,
    val id: String,
    val media: Media,
    val name: String,
    val releaseDate: String,
    val retailPrice: Int,
    val shoe: String,
    val styleId: String,
    val title: String,
    val year: Int
)
fun SneakerDetailDtoX.toSneakerDetail(): SneakerDetail {
    return SneakerDetail(
        SneakerId = id,
        name = name,
        image = media,
        brand = brand,
        year = year,
        title = title,
        price = retailPrice

    )
}