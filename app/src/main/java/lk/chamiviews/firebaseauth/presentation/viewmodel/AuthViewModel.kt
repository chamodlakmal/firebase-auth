package lk.chamiviews.firebaseauth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import lk.chamiviews.firebaseauth.domain.model.UserDomain
import lk.chamiviews.firebaseauth.domain.usecase.LoginUseCase
import lk.chamiviews.firebaseauth.domain.usecase.RegisterUseCase
import lk.chamiviews.firebaseauth.presentation.events.AuthEvent
import lk.chamiviews.firebaseauth.presentation.state.LoginState
import lk.chamiviews.firebaseauth.presentation.state.RegisterState
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase
) : ViewModel() {
    private val _loginState = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> = _loginState

    private val _registerState = MutableStateFlow(RegisterState())
    val registerState: StateFlow<RegisterState> = _registerState

    fun onEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.Login -> {
                login(event.email, event.password)
            }

            is AuthEvent.Register -> {
                register(event.email, event.password)
            }
        }

    }

    private fun login(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _loginState.update {
                it.copy(isLoading = true, errorTxt = null, userDomain = null)
            }
            loginUseCase(email, password).collect { result ->
                _loginState.value = LoginState(
                    isLoading = false,
                    userDomain = result.getOrNull(),
                    errorTxt = result.exceptionOrNull()?.message
                )
            }
        }
    }

    private fun register(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _registerState.update {
                it.copy(isLoading = true, errorTxt = null, userDomain = null)
            }
            registerUseCase(email, password).collect { result ->
                _registerState.value = RegisterState(
                    isLoading = false,
                    userDomain = result.getOrNull(),
                    errorTxt = result.exceptionOrNull()?.message
                )

            }
        }
    }
}