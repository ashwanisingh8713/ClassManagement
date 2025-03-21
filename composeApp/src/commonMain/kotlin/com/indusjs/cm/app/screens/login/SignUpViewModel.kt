package com.indusjs.cm.app.screens.login

import com.indusjs.cm.app.model.ResourceUiState
import com.indusjs.cm.app.mvi.MviBaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SignUpViewModel(coroutineScope: CoroutineScope): MviBaseViewModel<LoginContract.Event, LoginContract.State, LoginContract.Effect>(coroutineScope) {

    private val _successState = MutableStateFlow<String?>(null)
    val successState: StateFlow<String?> get() = _successState

    override fun createInitialState(): LoginContract.State = LoginContract.State(loginResponse = ResourceUiState.Idle)

    override fun handleEvent(event: LoginContract.Event) {

    }


}