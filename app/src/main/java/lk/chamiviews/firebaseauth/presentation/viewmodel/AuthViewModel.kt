package lk.chamiviews.firebaseauth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import lk.chamiviews.firebaseauth.domain.model.UserDomain
import lk.chamiviews.firebaseauth.domain.usecase.LoginUseCase
import lk.chamiviews.firebaseauth.presentation.events.LoginEvent
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    private val _loginState = MutableStateFlow<Result<UserDomain>?>(null)
    val loginState: StateFlow<Result<UserDomain>?> = _loginState

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.Submit -> {
                login(event.email, event.password)
            }
        }

    }

    private fun login(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            loginUseCase(email, password).collect {
                _loginState.value = it
            }
        }
    }
}