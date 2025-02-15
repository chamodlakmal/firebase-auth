package lk.chamiviews.firebaseauth.data.mapper

import lk.chamiviews.firebaseauth.data.model.ProductDto
import lk.chamiviews.firebaseauth.domain.model.ProductDomain
import lk.chamiviews.firebaseauth.domain.model.RatingDomain

fun ProductDto.toDomain(): ProductDomain {
    return ProductDomain(
        id = this.id,
        title = this.title,
        price = this.price,
        description = this.description,
        category = this.category,
        image = this.image,
        rating = RatingDomain(
            rate = this.rating.rate,
            count = this.rating.count
        )
    )
}
