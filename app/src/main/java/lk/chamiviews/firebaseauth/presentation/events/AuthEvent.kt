package lk.chamiviews.firebaseauth.presentation.events

sealed class AuthEvent {
    data class Login(val email: String, val password: String) : AuthEvent()
    data class Register(val email: String, val password: String) : AuthEvent()
}