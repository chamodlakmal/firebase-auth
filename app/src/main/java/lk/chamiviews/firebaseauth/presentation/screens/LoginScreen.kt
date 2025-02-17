package lk.chamiviews.firebaseauth.presentation.screens

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

@Composable
fun LoginScreen(
    loginState: LoginState?, onEvent: (AuthEvent) -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var isValidEmail by remember { mutableStateOf(false) }
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
        if (!isValidEmail) {
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

        if (password.length < 6) {
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

        ButtonComponent(text = "Login",
            isLoading = loginState?.isLoading == true,
            enabled = isValidEmail && loginState?.isLoading == false && password.length > 5,
            onClick = {
                onEvent(AuthEvent.Login(email, password))
            })

        if (loginState?.errorTxt != null) {
            Text("Error: ${loginState.errorTxt}", color = Color.Red)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(loginState = LoginState(), onEvent = {})
}