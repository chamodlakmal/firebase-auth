package lk.chamiviews.firebaseauth.data

import lk.chamiviews.firebaseauth.data.model.ProductDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductService {
    @GET("products")
    suspend fun getProducts(): List<ProductDto>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") productId: Int): ProductDto
}