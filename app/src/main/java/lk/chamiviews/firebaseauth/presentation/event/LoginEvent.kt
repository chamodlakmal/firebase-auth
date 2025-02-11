package lk.chamiviews.firebaseauth.presentation.event

sealed class LoginEvent {
    data class Submit(val email: String, val password: String) : LoginEvent()
}