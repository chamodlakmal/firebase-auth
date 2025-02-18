package lk.chamiviews.firebaseauth.presentation.state

import lk.chamiviews.firebaseauth.domain.model.ProductDomain

data class ProductDetailState(
    val isLoading: Boolean = true,
    val product: ProductDomain? = null,
    val errorMessage: String? = null
)


