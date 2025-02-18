package lk.chamiviews.firebaseauth.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun CommonCircularProgressIndicator(modifier: Modifier) {
    CircularProgressIndicator(
        color = Color.DarkGray,
        modifier = modifier
            .padding(16.dp)
    )
}