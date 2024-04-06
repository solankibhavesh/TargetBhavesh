package com.target.bhavesh.ui.screens.details.usecase

import com.target.bhavesh.domain.models.ProductDetails
import com.target.bhavesh.domain.repository.ProductRepositoryImpl
import com.target.bhavesh.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import java.net.SocketTimeoutException
import javax.inject.Inject

class FetchProductDetailsUseCase @Inject constructor(private val repository: ProductRepositoryImpl) {
    fun execute(productId: String): Flow<Resource<ProductDetails>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.getProductDetails(productId)
            if (response.isSuccessful) {
                val data = response.body()
                data.takeIf { it != null }?.let {
                    emit(Resource.Success(it))
                } ?: emit(Resource.Error("No data found"))
            }
        } catch (e: IOError) {
            emit(Resource.Error("No internet connection"))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Error"))
        } catch (e: SocketTimeoutException) {
            emit(Resource.Error("Timeout"))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unknown Error"))
        }
    }
}