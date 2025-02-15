package lk.chamiviews.firebaseauth.data

import lk.chamiviews.firebaseauth.data.model.ProductDto
import retrofit2.http.GET

interface ProductService {
    @GET("products")
    suspend fun getProducts(): List<ProductDto>
}