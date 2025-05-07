package com.indusjs.cm.app.viewmodels.login


import com.indusjs.cm.app.model.ResourceUiState
import com.indusjs.cm.app.presentations.mvi.MviBaseViewModel
import com.indusjs.cm.app.presentations.screens.login.LoginContract
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
            is LoginContract.Event.OnGoToHomeScreenClick -> setEffect{LoginContract.Effect.NavigateToHomeScreen(email = "")}
            is LoginContract.Event.OnLoginClick -> signInRequest()
            is LoginContract.Event.OnTryCheckAgainClick -> signInRequest()
            is LoginContract.Event.OnForgotPasswordClick -> {}
            else ->{}
        }
    }

    private fun signInRequest() {
        setState { copy(loginResponse = ResourceUiState.Loading) }
        coroutineScope.launch {
            signInUseCase("").onSuccess {
                setState { copy(loginResponse = ResourceUiState.Empty) }
            }.onFailure {  }
        }
    }


}