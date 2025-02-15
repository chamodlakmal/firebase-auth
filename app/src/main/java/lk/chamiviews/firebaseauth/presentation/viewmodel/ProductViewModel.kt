package lk.chamiviews.firebaseauth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import lk.chamiviews.firebaseauth.domain.usecase.GetProductsUseCase
import lk.chamiviews.firebaseauth.presentation.state.ProductState
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {
    private val _productState = MutableStateFlow(ProductState())
    val productState: StateFlow<ProductState> = _productState

    init {
        fetchProducts()
    }


    private fun fetchProducts() {
        viewModelScope.launch {
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

}