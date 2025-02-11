package lk.chamiviews.firebaseauth.presentation.events

sealed class LoginEvent {
    data class Submit(val email: String, val password: String) : LoginEvent()
}