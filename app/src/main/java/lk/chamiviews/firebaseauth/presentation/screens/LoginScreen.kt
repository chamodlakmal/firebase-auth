package lk.chamiviews.firebaseauth.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import lk.chamiviews.firebaseauth.data.model.User
import lk.chamiviews.firebaseauth.presentation.components.ButtonComponent
import lk.chamiviews.firebaseauth.presentation.components.TextFieldComponent
import lk.chamiviews.firebaseauth.presentation.events.LoginEvent

@Composable
fun LoginScreen(
    loginState: Result<User>?, onEvent: (LoginEvent) -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextFieldComponent(
            value = email,
            onValueChange = { email = it },
            label = "Email",
            icon = Icons.Default.Email
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextFieldComponent(
            value = password,
            onValueChange = { password = it },
            label = "Password",
            visualTransformation = PasswordVisualTransformation(),
            icon = Icons.Default.Lock
        )

        Spacer(modifier = Modifier.height(16.dp))

        ButtonComponent(text = "Login", isLoading = false) {
            onEvent(LoginEvent.Submit(email, password))
        }

        loginState?.let {
            when {
                it.isSuccess -> Text("Login Success: ${it.getOrNull()?.email}")
                it.isFailure -> Text("Error: ${it.exceptionOrNull()?.message}", color = Color.Red)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        loginState = Result.success(User("1", "william.henry.moody@my-own-personal-domain.com")),
        onEvent = {}
    )
}