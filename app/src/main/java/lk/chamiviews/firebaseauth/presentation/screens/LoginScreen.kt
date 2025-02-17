package lk.chamiviews.firebaseauth.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import lk.chamiviews.firebaseauth.domain.model.UserDomain
import lk.chamiviews.firebaseauth.presentation.components.ButtonComponent
import lk.chamiviews.firebaseauth.presentation.components.TextFieldComponent
import lk.chamiviews.firebaseauth.presentation.events.AuthEvent
import lk.chamiviews.firebaseauth.presentation.state.LoginState
import lk.chamiviews.firebaseauth.presentation.state.RegisterState

@Composable
fun AuthScreen(
    loginState: LoginState?, onEvent: (AuthEvent) -> Unit,
    registerState: RegisterState? = null
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var isValidEmail by remember { mutableStateOf(false) }
    var isLoginFlow by remember { mutableStateOf(true) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextFieldComponent(
            value = email, onValueChange = {
                isValidEmail = android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches()
                email = it
            }, label = "Email", icon = Icons.Default.Email
        )
        if (!isValidEmail && email.isNotEmpty()) {
            Text(
                "Invalid email",
                color = Color.Red,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 2.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextFieldComponent(
            value = password,
            onValueChange = { password = it },
            label = "Password",
            visualTransformation = PasswordVisualTransformation(),
            icon = Icons.Default.Lock
        )

        if (password.length < 6 && password.isNotEmpty()) {
            Text(
                "Password must be at least 6 characters",
                color = Color.Red,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 2.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        ButtonComponent(text = if (isLoginFlow) "Login" else "Register",
            isLoading = loginState?.isLoading == true || registerState?.isLoading == true,
            enabled = isValidEmail && loginState?.isLoading == false && registerState?.isLoading == false && password.length > 5,
            onClick = {
                if (isLoginFlow) {
                    onEvent(AuthEvent.Login(email, password))
                } else {
                    onEvent(AuthEvent.Register(email, password))
                }
            })

        if (loginState?.errorTxt != null) {
            Text("Error: ${loginState?.errorTxt}", color = Color.Red)
        }

        if (registerState?.errorTxt != null) {
            Text("Error: ${registerState.errorTxt}", color = Color.Red)
        }

        Text(text = if (isLoginFlow) "Don't have an account? Register" else "Already have an account? Login",
            modifier = Modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = {
                    isLoginFlow = !isLoginFlow
                }
            ))
    }
}


@Preview(showBackground = true)
@Composable
fun AuthScreenPreview() {
    AuthScreen(loginState = LoginState(), onEvent = {})
}