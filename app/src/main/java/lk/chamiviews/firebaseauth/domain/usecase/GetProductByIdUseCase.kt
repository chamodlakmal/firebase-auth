package lk.chamiviews.firebaseauth.domain.usecase

import kotlinx.coroutines.flow.Flow
import lk.chamiviews.firebaseauth.domain.model.ProductDomain
import lk.chamiviews.firebaseauth.domain.repository.ProductRepository

class GetProductByIdUseCase(private val repository: ProductRepository) {
    operator fun invoke(id: Int): Flow<Result<ProductDomain>> {
        return repository.getProductById(id)
    }
}
