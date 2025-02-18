package lk.chamiviews.firebaseauth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable
import lk.chamiviews.firebaseauth.presentation.args.ProductDetailScreenArgs
import lk.chamiviews.firebaseauth.presentation.args.ProductListScreenArgs
import lk.chamiviews.firebaseauth.presentation.screens.ProductDetailScreen
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
                val navController = rememberNavController()
                NavHost(
                    navController = navController, startDestination = ProductListScreenArgs
                ) {
                    composable<ProductListScreenArgs> {
                        ProductListScreen(productState = viewModel.productState.collectAsState().value,
                            navigateToProductDetail = {
                                viewModel.getProductById(it)
                                navController.navigate(ProductDetailScreenArgs(it))
                            })
                    }
                    composable<ProductDetailScreenArgs> {
                        ProductDetailScreen(viewModel.productDetailState.collectAsState().value)
                    }
                }
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