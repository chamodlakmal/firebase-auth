package lk.chamiviews.firebaseauth.presentation.components

import androidx.compose.foundation.background
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.TopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonTopAppBar(title: String) {
    Surface(shadowElevation = 4.dp) {
        TopAppBar(colors = topAppBarColors(
            containerColor = Color(0xFFFFFFFF), titleContentColor = Color(0xFF424242)
        ), modifier = Modifier.background(Color.White), title = {
            Text(
                text = title
            )
        })
    }
}

@Preview(showBackground = true)
@Composable
fun CommonTopAppBarPreview() {
    CommonTopAppBar(title = "Product")
}