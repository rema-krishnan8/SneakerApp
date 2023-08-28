package com.iexceed.sneaker.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sneaker_cart_table")
data class SneakerCart(
    @PrimaryKey(autoGenerate = true)
    val id : Int =0,
    val SneakerId: String,
    val name: String,
    val image: String,
    val brand: String,
    val year: Int,
    val title: String,
    val price: Int
)
