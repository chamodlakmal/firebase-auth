package lk.chamiviews.firebaseauth.presentation.state

import lk.chamiviews.firebaseauth.domain.model.UserDomain

data class LoginState(
    val isLoading: Boolean = false, val userDomain: UserDomain? = null, val errorTxt: String? = null
)

data class RegisterState(
    val isLoading: Boolean = false, val userDomain: UserDomain? = null, val errorTxt: String? = null
)