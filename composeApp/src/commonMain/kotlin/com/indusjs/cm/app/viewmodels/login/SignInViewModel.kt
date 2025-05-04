package com.indusjs.cm.app.viewmodels.login


import com.indusjs.cm.app.model.ResourceUiState
import com.indusjs.cm.app.presentations.mvi.MviBaseViewModel
import com.indusjs.cm.app.presentations.screens.login.LoginContract
import com.indusjs.cm.domain.usecase.login.SignInUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SignInViewModel(private val signInUseCase: SignInUseCase, coroutineScope: CoroutineScope): MviBaseViewModel<LoginContract.Event, LoginContract.SignInState, LoginContract.Effect>(coroutineScope) {

    val _successState = MutableStateFlow<String?>(null)
    val successState: StateFlow<String?> get() = _successState

    fun isValidEmail(email: String): Boolean {

        return true;
    }

    fun doLogin(): Unit {

    }

    override fun createInitialState(): LoginContract.SignInState = LoginContract.SignInState(loginResponse = ResourceUiState.Idle)

    override fun handleEvent(event: LoginContract.Event) {
        when(event) {
            is LoginContract.Event.OnSignUpClick -> signInRequest()
            is LoginContract.Event.OnForgotPasswordClick -> signInRequest()
            is LoginContract.Event.OnLoginClick -> signInRequest()
            is LoginContract.Event.OnTryCheckAgainClick -> signInRequest()
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