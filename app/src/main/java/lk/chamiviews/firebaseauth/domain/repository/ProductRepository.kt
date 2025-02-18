package lk.chamiviews.firebaseauth.domain.repository

import kotlinx.coroutines.flow.Flow
import lk.chamiviews.firebaseauth.domain.model.ProductDomain


interface ProductRepository {
    fun getProducts(): Flow<Result<List<ProductDomain>>>
    fun getProductById(id: Int): Flow<Result<ProductDomain>>
}