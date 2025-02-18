package lk.chamiviews.firebaseauth.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import lk.chamiviews.firebaseauth.domain.model.ProductDomain
import lk.chamiviews.firebaseauth.domain.model.RatingDomain
import lk.chamiviews.firebaseauth.presentation.components.CommonCircularProgressIndicator
import lk.chamiviews.firebaseauth.presentation.components.CommonTopAppBar
import lk.chamiviews.firebaseauth.presentation.state.ProductDetailState


@Composable
fun ProductDetailScreen(productDetailState: ProductDetailState) {
    Scaffold(topBar = {
        CommonTopAppBar(title = "Product Details")
    }, content = {
        Column(
            modifier = Modifier
                .padding(
                    top = it.calculateTopPadding(), bottom = it.calculateBottomPadding()
                )
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp)
        ) {
            if (productDetailState.isLoading) {
                CommonCircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            } else if (productDetailState.errorMessage != null) {
                Text(
                    "Error: ${productDetailState.errorMessage}",
                    color = Color.Red,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            } else {
                Column {
                    AsyncImage(
                        model = productDetailState.product?.image,
                        contentDescription = productDetailState.product?.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Product Title
                    Text(
                        text = productDetailState.product?.title ?: "",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    // Product Category
                    Text(
                        text = "Category: ${productDetailState.product?.category}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Product Description
                    Text(
                        text = productDetailState.product?.description ?: "",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Product Price
                    Text(
                        text = "Price: $${productDetailState.product?.price}",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF388E3C)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Product Rating
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Rating: ${productDetailState.product?.rating?.rate} ⭐",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "(${productDetailState.product?.rating?.count} reviews)",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Black
                        )
                    }
                }

            }
            // Product Image

        }
    })

}


@Preview(showBackground = true)
@Composable
private fun ProductDetailScreenPreview() {
    ProductDetailScreen(
        productDetailState = ProductDetailState(
            isLoading = true, product = ProductDomain(
                id = 1,
                title = "Sample Product",
                price = 19.99,
                description = "This is a sample product description to showcase the preview.",
                category = "Electronics",
                image = "https://via.placeholder.com/150",
                rating = RatingDomain(rate = 4.5, count = 120)
            ), errorMessage = null
        )
    )
}
