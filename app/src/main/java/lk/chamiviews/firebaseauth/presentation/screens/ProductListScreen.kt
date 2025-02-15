package lk.chamiviews.firebaseauth.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import lk.chamiviews.firebaseauth.domain.model.ProductDomain
import lk.chamiviews.firebaseauth.domain.model.RatingDomain
import lk.chamiviews.firebaseauth.presentation.components.ProductItemComponent
import lk.chamiviews.firebaseauth.presentation.state.ProductState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(
    productState: ProductState
) {
    Scaffold(topBar = {
        Surface(shadowElevation = 4.dp) {
            TopAppBar(colors = topAppBarColors(
                containerColor = Color(0xFFFFFFFF), titleContentColor = Color(0xFF424242)
            ), modifier = Modifier.background(Color.White), title = {
                Text(
                    text = "Products"
                )
            })
        }
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF0F0F0))
                .padding(
                    top = it.calculateTopPadding(), bottom = it.calculateBottomPadding()
                ),
            verticalArrangement = Arrangement.Center
        ) {
            when {
                productState.isLoading -> {
                    CircularProgressIndicator(
                        color = Color.DarkGray,
                        modifier = Modifier
                            .align(alignment = Alignment.CenterHorizontally)
                            .padding(16.dp)
                    )
                }

                productState.errorMessage != null -> {
                    // Show error message when data fails to load
                    Text(
                        text = "Failed to load products: ${productState.errorMessage}",
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }

                productState.products.isNotEmpty() -> {
                    // Display products in a LazyColumn when data is available
                    LazyColumn {
                        items(productState.products) { product ->
                            ProductItemComponent(product)
                        }
                    }
                }

                else -> {
                    Text(
                        text = "No products available",
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }

        }
    }

}


@Preview(showBackground = true)
@Composable
fun ProductListScreenPreview() {
    val sampleProductState = ProductState(
        isLoading = true,
        products = listOf(
            ProductDomain(
                id = 1,
                title = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
                price = 109.95,
                description = "Your perfect pack for everyday use and walks in the forest.",
                category = "men's clothing",
                image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
                rating = RatingDomain(rate = 3.9, count = 120)
            )
        ),
        errorMessage = null
    )
    ProductListScreen(productState = sampleProductState)
}