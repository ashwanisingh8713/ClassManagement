package com.indusjs.cm.app.screens.login

import com.indusjs.cm.app.model.ResourceUiState
import com.indusjs.cm.app.mvi.IUiEffect
import com.indusjs.cm.app.mvi.IUiEvent
import com.indusjs.cm.app.mvi.IUiState
import com.indusjs.cm.domain.model.login.SignInResponse

interface LoginContract {
    sealed interface Event : IUiEvent {
        object OnTryCheckAgainClick : Event
        object OnSignUpClick : Event
        object OnForgotPasswordClick : Event
        data class OnLoginClick(val email: String, val password: String) : Event
    }

    data class State(
        val loginResponse: ResourceUiState<SignInResponse>
    ) : IUiState

    sealed interface Effect : IUiEffect {
        data class NavigateToForgotPasswordScreen(val email: String) : Effect
        data class NavigateToHomeScreen(val email: String) : Effect
        object NavigateToSignUpScreen : Effect
    }
}