package com.indusjs.cm.app.presentations.screens.login

import com.indusjs.cm.app.model.ResourceUiState
import com.indusjs.cm.app.presentations.mvi.MviBaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SignUpViewModel(coroutineScope: CoroutineScope): MviBaseViewModel<LoginContract.Event, LoginContract.SignUpState, LoginContract.Effect>(coroutineScope) {

    private val _successState = MutableStateFlow<String?>(null)
    val successState: StateFlow<String?> get() = _successState

    override fun createInitialState(): LoginContract.SignUpState =
        LoginContract.SignUpState(loginResponse = ResourceUiState.Idle)

    override fun handleEvent(event: LoginContract.Event) {

    }


}