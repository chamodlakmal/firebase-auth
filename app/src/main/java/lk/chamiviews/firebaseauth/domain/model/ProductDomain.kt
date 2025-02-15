package lk.chamiviews.firebaseauth.domain.model

data class ProductDomain(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: RatingDomain
)

