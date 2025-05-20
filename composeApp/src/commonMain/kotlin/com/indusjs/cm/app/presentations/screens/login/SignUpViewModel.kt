package com.indusjs.cm.app.presentations.screens.login

import com.indusjs.cm.app.model.ResourceUiState
import com.indusjs.cm.app.viewmodels.mvi.MviBaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SignUpViewModel(coroutineScope: CoroutineScope): MviBaseViewModel<LoginContract.Event, LoginContract.SignUpState, LoginContract.Effect>(coroutineScope) {

    override fun createInitialState(): LoginContract.SignUpState =
        LoginContract.SignUpState(loginResponse = ResourceUiState.Idle)

    override fun handleEvent(event: LoginContract.Event) {
        when(event) {
            is LoginContract.Event.OnBackToSignInClick -> setEffect { LoginContract.Effect.NavigateToSignInScreen }
            else -> { }
        }
    }


}