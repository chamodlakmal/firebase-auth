package lk.chamiviews.firebaseauth.domain.mapper

import lk.chamiviews.firebaseauth.data.model.User
import lk.chamiviews.firebaseauth.domain.model.UserDomain

fun User.toDomain(): UserDomain {
    return UserDomain(
        id = this.uid,
        email = this.email ?: ""
    )
}