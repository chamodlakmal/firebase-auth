package lk.chamiviews.firebaseauth.domain.usecase

import kotlinx.coroutines.flow.Flow
import lk.chamiviews.firebaseauth.domain.model.ProductDomain
import lk.chamiviews.firebaseauth.domain.repository.ProductRepository

class GetProductsUseCase(private val repository: ProductRepository) {
    operator fun invoke(): Flow<Result<List<ProductDomain>>> {
        return repository.getProducts()
    }
}
