package com.target.bhavesh.ui.screens.home.usecase

import com.target.bhavesh.domain.models.Product
import com.target.bhavesh.domain.repository.ProductRepositoryImpl
import com.target.bhavesh.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOError
import java.net.SocketTimeoutException
import javax.inject.Inject

class FetchProductsUseCase @Inject constructor(private val repository: ProductRepositoryImpl) {
    fun execute(): Flow<Resource<List<Product>>> = flow {
        emit(Resource.Loading())
        val response = repository.getProducts()
        if (response.isSuccessful) {
            val data = response.body()
            data.takeIf { it != null }?.takeIf { !it.products.isNullOrEmpty() }?.products?.let {
                emit(Resource.Success(it))
            } ?: emit(Resource.Error("No data found"))
        }
    }.flowOn(Dispatchers.IO)
        .catch {
            when (it) {
                is IOError -> emit(Resource.Error("No internet connection"))
                is HttpException -> emit(Resource.Error(it.localizedMessage ?: "Error"))
                is SocketTimeoutException -> emit(Resource.Error("Timeout"))
                is Exception -> emit(Resource.Error(it.localizedMessage ?: "Unknown Error"))
            }
        }
}