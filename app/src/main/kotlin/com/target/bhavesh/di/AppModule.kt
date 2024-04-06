package com.target.bhavesh.di

import com.target.bhavesh.domain.ProductApi
import com.target.bhavesh.domain.repository.ProductRepositoryImpl
import com.target.bhavesh.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideProductApi(): ProductApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductRepository(api: ProductApi): ProductRepositoryImpl {
        return ProductRepositoryImpl(api = api)
    }
}