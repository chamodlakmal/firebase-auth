package lk.chamiviews.firebaseauth.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import lk.chamiviews.firebaseauth.domain.model.ProductDomain

@Composable
fun ProductItemComponent(product: ProductDomain) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp), colors = CardColors(
            containerColor = Color.White, contentColor = Color.Black,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Transparent
        ), elevation = CardDefaults.elevatedCardElevation()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = product.image,
                contentDescription = null,
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Crop
            )
            Text(text = product.title, style = MaterialTheme.typography.titleMedium)
            Text(text = "$${product.price}", style = MaterialTheme.typography.bodyLarge)
        }
    }
}