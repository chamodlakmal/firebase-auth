package lk.chamiviews.firebaseauth.domain.repository

import kotlinx.coroutines.flow.Flow
import lk.chamiviews.firebaseauth.domain.model.UserDomain

interface AuthRepository {
    fun login(email: String, password: String): Flow<Result<UserDomain>>
    fun register(email: String, password: String): Flow<Result<UserDomain>>
}