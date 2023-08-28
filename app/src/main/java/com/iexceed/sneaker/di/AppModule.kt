package com.iexceed.sneaker.di

import android.content.Context
import androidx.room.Room
import com.iexceed.sneaker.common.Constants
import com.iexceed.sneaker.data.remote.SneakerCartDao
import com.iexceed.sneaker.data.remote.SneakerCartDatabase
import com.iexceed.sneaker.data.remote.SneakerRetrofitApi
import com.iexceed.sneaker.data.repository.SneakerCartRepositoryImpl
import com.iexceed.sneaker.data.repository.SneakerRepositoryImpl
import com.iexceed.sneaker.domain.repository.SneakerCartRepositoryApi
import com.iexceed.sneaker.domain.repository.SneakerRepositoryApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofitApi(): SneakerRetrofitApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SneakerRetrofitApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepositoryApi(api: SneakerRetrofitApi): SneakerRepositoryApi {
        return SneakerRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): SneakerCartDatabase {
        return SneakerCartDatabase.getDatabase(appContext)
    }

    @Provides
    fun provideChannelDao(appDatabase: SneakerCartDatabase): SneakerCartDao {
        return appDatabase.cartDao()
    }
    @Provides
    @Singleton
    fun providesCartRepositoryApi(api: SneakerCartDao): SneakerCartRepositoryApi{
        return SneakerCartRepositoryImpl(api)
    }
}
