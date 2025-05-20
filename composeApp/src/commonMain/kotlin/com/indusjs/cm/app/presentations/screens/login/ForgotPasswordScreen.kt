package com.indusjs.cm.app.presentations.screens.login

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.indusjs.cm.app.presentations.utils.NavigationRoute
import com.indusjs.cm.app.viewmodels.login.SignInViewModel
import org.koin.compose.koinInject

@Composable
fun ForgotPasswordScreen(
    navController: NavHostController,
    forgotPasswordViewModel: SignInViewModel = koinInject<SignInViewModel>()
) {

}