package lk.chamiviews.firebaseauth.presentation.state

import lk.chamiviews.firebaseauth.domain.model.ProductDomain

data class ProductState(
    val isLoading: Boolean = true,
    val products: List<ProductDomain> = emptyList(),
    val errorMessage: String? = null
)


