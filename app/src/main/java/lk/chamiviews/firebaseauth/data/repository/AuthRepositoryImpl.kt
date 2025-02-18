package lk.chamiviews.firebaseauth.data.repository

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import lk.chamiviews.firebaseauth.data.model.User
import lk.chamiviews.firebaseauth.domain.mapper.toDomain
import lk.chamiviews.firebaseauth.domain.model.UserDomain
import lk.chamiviews.firebaseauth.domain.repository.AuthRepository

class AuthRepositoryImpl(private val firebaseAuth: FirebaseAuth) : AuthRepository {
    override fun login(email: String, password: String): Flow<Result<UserDomain>> = flow {
        try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            emit(Result.success(User(result.user?.uid ?: "", result.user?.email).toDomain()))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override fun register(email: String, password: String): Flow<Result<UserDomain>> = flow {

    }
}