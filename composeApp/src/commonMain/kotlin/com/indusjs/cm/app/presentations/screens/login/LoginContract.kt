package com.indusjs.cm.app.presentations.screens.login

import SignInResponse
import com.indusjs.cm.app.model.ResourceUiState
import com.indusjs.cm.app.viewmodels.mvi.IUiEffect
import com.indusjs.cm.app.viewmodels.mvi.IUiEvent
import com.indusjs.cm.app.viewmodels.mvi.IUiState
import com.indusjs.cm.data.repo.UserType
import com.indusjs.cm.domain.model.login.SignUpResponse

interface LoginContract {
    sealed interface Event : IUiEvent {
        data class OnTryCheckAgainClick(val email: String, val password: String) : Event
        data object OnSignUpClick : Event
        data class OnForgotPasswordClick(val email: String) : Event
        data object OnBackToSignInClick : Event
        data class OnGoToHomeScreenClick(val signInResponse: SignInResponse) : Event
        data class OnLoginClick(val email: String, val password: String, val userType: UserType) : Event
    }

    data class SignInState(
        val loginResponse: ResourceUiState<SignInResponse>
    ) : IUiState

    data class SignUpState(
        val loginResponse: ResourceUiState<SignUpResponse>
    ) : IUiState


    sealed interface Effect : IUiEffect {
        data class NavigateToForgotPasswordScreen(val email: String) : Effect
        data class NavigateToHomeScreen(val signInResponse: SignInResponse) : Effect
        data object NavigateToSignUpScreen : Effect
        data object NavigateToSignInScreen : Effect
        data object IDLE : Effect
    }
}