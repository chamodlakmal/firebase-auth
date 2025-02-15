package lk.chamiviews.firebaseauth.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import lk.chamiviews.firebaseauth.data.ProductService
import lk.chamiviews.firebaseauth.data.repository.ProductRepositoryImpl
import lk.chamiviews.firebaseauth.domain.repository.ProductRepository
import lk.chamiviews.firebaseauth.domain.usecase.GetProductsUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://fakestoreapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ProductService =
        retrofit.create(ProductService::class.java)

    @Provides
    @Singleton
    fun provideProductRepository(apiService: ProductService): ProductRepository =
        ProductRepositoryImpl(apiService)

    @Provides
    @Singleton
    fun provideGetProductsUseCase(repository: ProductRepository) = GetProductsUseCase(repository)

}