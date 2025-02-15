package lk.chamiviews.firebaseauth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import lk.chamiviews.firebaseauth.presentation.screens.ProductListScreen
import lk.chamiviews.firebaseauth.presentation.viewmodel.ProductViewModel
import lk.chamiviews.firebaseauth.ui.theme.FirebaseauthTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FirebaseauthTheme {
                val viewModel: ProductViewModel = hiltViewModel()

                ProductListScreen(
                    productState = viewModel.productState.collectAsState().value,
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FirebaseauthTheme {

    }
}