package com.iexceed.sneaker

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.iexceed.sneaker.data.remote.SneakerCartDao
import com.iexceed.sneaker.data.remote.SneakerCartDatabase
import com.iexceed.sneaker.domain.model.SneakerCart
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*
import javax.inject.Inject
import javax.inject.Named
import kotlin.random.Random

@HiltAndroidTest
@SmallTest
class SneakerDaoTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @Inject
    @Named("test_db")
    lateinit var database: SneakerCartDatabase
    private lateinit var sneakerCartDao: SneakerCartDao

    @Before
    fun setup(){
        hiltRule.inject()
        sneakerCartDao = database.cartDao()
    }

    @After
    fun tearDown(){
        database.close()
    }

    @Test
    fun insertUser() = runBlockingTest {
        val sneakerCart = SneakerCart(
            id = 30,
            SneakerId = "bhbdmdkdmskd",
            name = "name",
            image = "imageUrl",
            brand = "brand",
            year = 2020,
            title = "title",
            price = 200
        )
        sneakerCartDao.insert(sneakerCart)
        val allCart = sneakerCartDao.getCartSneakers()
        Assert.assertEquals(1, allCart.collectLatest { it.size })
    }
}


