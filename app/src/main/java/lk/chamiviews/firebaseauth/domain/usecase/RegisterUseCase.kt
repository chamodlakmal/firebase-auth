package lk.chamiviews.firebaseauth.domain.usecase

import lk.chamiviews.firebaseauth.domain.repository.AuthRepository

class RegisterUseCase(private val authRepository: AuthRepository) {
    operator fun invoke(email: String, password: String) = authRepository.register(email, password)
}