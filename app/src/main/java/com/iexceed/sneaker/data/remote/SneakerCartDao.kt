package com.iexceed.sneaker.data.remote

import androidx.lifecycle.LiveData
import androidx.room.*
import com.iexceed.sneaker.domain.model.SneakerCart
import kotlinx.coroutines.flow.Flow

@Dao
interface SneakerCartDao {
    @Query("SELECT * FROM sneaker_cart_table")
    fun getCartSneakers(): Flow<List<SneakerCart>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(cart: SneakerCart)

    @Delete
    suspend fun delete(item: SneakerCart)
}