package com.indusjs.cm.app.presentations.screens.login

import com.indusjs.cm.app.model.ResourceUiState
import com.indusjs.cm.app.presentations.mvi.IUiEffect
import com.indusjs.cm.app.presentations.mvi.IUiEvent
import com.indusjs.cm.app.presentations.mvi.IUiState
import com.indusjs.cm.domain.model.login.SignInResponse
import com.indusjs.cm.domain.model.login.SignupResponse

interface LoginContract {
    sealed interface Event : IUiEvent {
        data object OnTryCheckAgainClick : Event
        data object OnSignUpClick : Event
        data object OnForgotPasswordClick : Event
        data class OnLoginClick(val email: String, val password: String) : Event
    }

    data class SignInState(
        val loginResponse: ResourceUiState<SignInResponse>
    ) : IUiState

    data class SignUpState(
        val loginResponse: ResourceUiState<SignupResponse>
    ) : IUiState


    sealed interface Effect : IUiEffect {
        data class NavigateToForgotPasswordScreen(val email: String) : Effect
        data class NavigateToHomeScreen(val email: String) : Effect
        data object NavigateToSignUpScreen : Effect
    }
}