package com.indusjs.cm.app.presentations.screens.profle

import com.indusjs.cm.app.presentations.mvi.IUiEvent
import com.indusjs.cm.app.presentations.mvi.IUiState
import com.indusjs.cm.domain.model.profile.ProfileResponse

interface ProfileContract {

    sealed interface Event:IUiEvent {
        data object OnTryCheckAgainClick : Event
        data object OnEditClick :Event
        data object OnSaveClick :Event
        data object OnProfileEditClick :Event
    }

    data class State(val response: ProfileResponse):IUiState

    sealed interface Effect {
        data class NavigateToEditProfileScreen(val email: String) : Effect
        data class NavigateToHomeScreen(val email: String) : Effect

    }
}