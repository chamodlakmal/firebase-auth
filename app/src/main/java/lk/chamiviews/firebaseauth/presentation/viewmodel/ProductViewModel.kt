package lk.chamiviews.firebaseauth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import lk.chamiviews.firebaseauth.domain.usecase.GetProductByIdUseCase
import lk.chamiviews.firebaseauth.domain.usecase.GetProductsUseCase
import lk.chamiviews.firebaseauth.presentation.state.ProductDetailState
import lk.chamiviews.firebaseauth.presentation.state.ProductState
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {
    private val _productState = MutableStateFlow(ProductState())
    val productState: StateFlow<ProductState> = _productState

    private val _productDetailState = MutableStateFlow(ProductDetailState())
    val productDetailState: StateFlow<ProductDetailState> = _productDetailState

    private val productExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _productState.value = ProductState(
            errorMessage = exception.localizedMessage ?: "An unknown error occurred",
            isLoading = false
        )
    }

    init {
        fetchProducts()
    }


    private fun fetchProducts() {
        viewModelScope.launch(Dispatchers.IO + productExceptionHandler) {
            _productState.value = ProductState(isLoading = true)

            getProductsUseCase().collect { result ->
                _productState.value = result.fold(
                    onSuccess = { products ->
                        ProductState(
                            products = products,
                            isLoading = false
                        )
                    },
                    onFailure = { error ->
                        ProductState(
                            errorMessage = error.message,
                            isLoading = false
                        )
                    }
                )
            }
        }
    }

    fun getProductById(id: Int) {

    }

}