package lk.chamiviews.firebaseauth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import lk.chamiviews.firebaseauth.data.model.User
import lk.chamiviews.firebaseauth.domain.usecase.LoginUseCase
import lk.chamiviews.firebaseauth.presentation.event.LoginEvent
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    private val _loginState = MutableStateFlow<Result<User>?>(null)
    val loginState: StateFlow<Result<User>?> = _loginState

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.Submit -> {
                login(event.email, event.password)
            }
        }

    }


    fun login(email: String, password: String) {
        viewModelScope.launch {
            loginUseCase(email, password).collect {
                _loginState.value = it
            }
        }
    }
}