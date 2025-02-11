package lk.chamiviews.firebaseauth.domain.repository

import kotlinx.coroutines.flow.Flow
import lk.chamiviews.firebaseauth.data.model.User

interface AuthRepository {
    fun login(email: String, password: String): Flow<Result<User>>
    fun register(email: String, password: String): Flow<Result<User>>
}