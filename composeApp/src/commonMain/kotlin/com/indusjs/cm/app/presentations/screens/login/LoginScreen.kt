package com.indusjs.cm.app.presentations.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import classmanagement.composeapp.generated.resources.Res
import classmanagement.composeapp.generated.resources.email
import classmanagement.composeapp.generated.resources.enter_your_password
import classmanagement.composeapp.generated.resources.forgot_password
import classmanagement.composeapp.generated.resources.password
import classmanagement.composeapp.generated.resources.sign_in
import classmanagement.composeapp.generated.resources.sign_up
import com.indusjs.cm.app.presentations.utils.NavigationRoute
import com.indusjs.cm.app.viewmodels.login.SignInViewModel
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

val LoginScreen:String = "login_screen"

@Composable
fun LoginScreen(navController: NavHostController, signInViewModel:SignInViewModel = koinInject<SignInViewModel>()) {

    LaunchedEffect(key1 = Unit) {
        signInViewModel.effect.collectLatest { effect->
            when(effect) {
                is LoginContract.Effect.NavigateToSignUpScreen -> navController.navigate(SignUpScreen)
                is LoginContract.Effect.NavigateToHomeScreen -> navController.navigate(NavigationRoute.TabsMainScreen.route) {
                    popUpTo(LoginScreen) {
                        inclusive = true
                    }
                }
                else -> {}
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.background)) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            ) {
            val username = remember { mutableStateOf(TextFieldValue()) }
            val password = remember { mutableStateOf(TextFieldValue()) }

            Text(
                text = stringResource(Res.string.sign_in),
                style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive)
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                value = username.value,
                onValueChange = { username.value = it },
                label = { Text(stringResource(Res.string.email)) },
                placeholder = { Text(stringResource(Res.string.enter_your_password)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text(stringResource(Res.string.password)) },
                placeholder = { Text(stringResource(Res.string.enter_your_password)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                        signInViewModel.setEvent(LoginContract.Event.OnLoginClick(email = "", password = ""))
                          },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(Res.string.sign_in))
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                    signInViewModel.setEvent(event =  LoginContract.Event.OnSignUpClick)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(Res.string.sign_up))
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                    signInViewModel.setEvent(LoginContract.Event.OnForgotPasswordClick)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(Res.string.forgot_password))
            }
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = {
                        //signInViewModel.setEvent(LoginContract.Event.OnGoToHomeScreenClick)
                              },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("GoTo HomeScreen")
                }
                Spacer(modifier = Modifier.width(10.dp))
                IconButton(
                    onClick = { /* TODO: Sign In with Facebook */ },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Sign In with Facebook")
                }
            }
        }
    }
}