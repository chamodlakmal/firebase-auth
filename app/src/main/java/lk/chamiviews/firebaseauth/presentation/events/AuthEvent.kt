package lk.chamiviews.firebaseauth.presentation.events

sealed class AuthEvent {
    data class Login(val email: String, val password: String) : AuthEvent()
}