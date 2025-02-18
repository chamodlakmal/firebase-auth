package lk.chamiviews.firebaseauth.presentation.args

import kotlinx.serialization.Serializable


@Serializable
object ProductListScreenArgs

@Serializable
data class ProductDetailScreenArgs(
    val id: Int
)