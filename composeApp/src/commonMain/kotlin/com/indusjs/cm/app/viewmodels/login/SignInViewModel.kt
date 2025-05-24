package com.indusjs.cm.app.viewmodels.login


import SignInResponse
import com.indusjs.cm.app.model.ResourceUiState
import com.indusjs.cm.app.viewmodels.mvi.MviBaseViewModel
import com.indusjs.cm.app.presentations.screens.login.LoginContract
import com.indusjs.cm.data.repo.LoginRequestBody
import com.indusjs.cm.domain.usecase.login.SignInUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class SignInViewModel(private val signInUseCase: SignInUseCase, coroutineScope: CoroutineScope): MviBaseViewModel<LoginContract.Event, LoginContract.SignInState, LoginContract.Effect>(coroutineScope) {


    fun isValidEmail(email: String): Boolean {

        return true;
    }

    override fun createInitialState(): LoginContract.SignInState = LoginContract.SignInState(loginResponse = ResourceUiState.Idle)

    override fun handleEvent(event: LoginContract.Event) {
        when(event) {
            is LoginContract.Event.OnSignUpClick -> setEffect{LoginContract.Effect.NavigateToSignUpScreen}
            is LoginContract.Event.OnForgotPasswordClick -> setEffect{LoginContract.Effect.NavigateToForgotPasswordScreen(event.email)}
            is LoginContract.Event.OnGoToHomeScreenClick -> {
                setEffect{LoginContract.Effect.NavigateToHomeScreen(event.signInResponse)}
            }
            is LoginContract.Event.OnLoginClick -> signInRequest(event.loginRequestBody)
            is LoginContract.Event.OnTryCheckAgainClick -> signInRequest(event.loginRequestBody)
            else ->{}
        }
    }

    private fun signInRequest(body: LoginRequestBody) {
        setState { copy(loginResponse = ResourceUiState.Loading) }
        coroutineScope.launch {
            signInUseCase(body).onSuccess<SignInResponse> { it ->
                if(it.success) {
                    println("Ashwani SignInViewModel Success...")
                    setState { copy(loginResponse = ResourceUiState.Success(it)) }
                    //setEffect { LoginContract.Effect.NavigateToHomeScreen(it) }
                } else {
                    println("Ashwani SignInViewModel Failure... ${it.message}")
                    setState { copy(loginResponse = ResourceUiState.Error(message = it.message)) }
                }


            }.onFailure { it ->
                println("Ashwani SignInViewModel Failure...")
                setState { copy(loginResponse = ResourceUiState.Error(message = it.message)) }
            }
        }
    }


}