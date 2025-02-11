package lk.chamiviews.firebaseauth.domain.usecase

import lk.chamiviews.firebaseauth.domain.repository.AuthRepository

class LoginUseCase(private val authRepository: AuthRepository) {
    operator fun invoke(email: String, password: String) = authRepository.login(email, password)
}