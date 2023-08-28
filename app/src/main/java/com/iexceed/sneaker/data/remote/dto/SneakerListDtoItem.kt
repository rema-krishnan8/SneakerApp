package com.iexceed.sneaker.data.remote.dto

import com.iexceed.sneaker.domain.model.Sneaker

data class SneakerListDtoItem(
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


//convert Sneaker DTo to Sneaker Model

fun SneakerListDtoItem.toSneaker() : Sneaker {
    return Sneaker(
        id = id,
        media = media.imageUrl,
        name = name,
        price = retailPrice
    )
}