package lk.chamiviews.firebaseauth.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import lk.chamiviews.firebaseauth.data.ProductService
import lk.chamiviews.firebaseauth.data.mapper.toDomain
import lk.chamiviews.firebaseauth.domain.model.ProductDomain
import lk.chamiviews.firebaseauth.domain.repository.ProductRepository

class ProductRepositoryImpl(private val apiService: ProductService) : ProductRepository {
    override fun getProducts(): Flow<Result<List<ProductDomain>>> = flow {
        try {
            val products = apiService.getProducts().map { it.toDomain() }
            emit(Result.success(products))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }

    }
}