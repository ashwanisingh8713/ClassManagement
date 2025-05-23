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
            is LoginContract.Event.OnLoginClick -> signInRequest(event.email, event.password)
            is LoginContract.Event.OnTryCheckAgainClick -> signInRequest(event.email, event.password)
            else ->{}
        }
    }

    private fun signInRequest(email:String, password:String) {
        setState { copy(loginResponse = ResourceUiState.Loading) }
        coroutineScope.launch {
            signInUseCase(LoginRequestBody(
                email = email,
                password = password
            )).onSuccess<SignInResponse> { it ->
                println("Ashwani SignInViewModel Success...")
                setState { copy(loginResponse = ResourceUiState.Success(it)) }
            }.onFailure { it ->
                println("Ashwani SignInViewModel Failure...")
                setState { copy(loginResponse = ResourceUiState.Error(message = it.message)) }
            }
        }
    }


}