package com.indusjs.cm.app.presentations.screens.home.tab1

import com.indusjs.cm.app.model.ResourceUiState
import com.indusjs.cm.app.viewmodels.mvi.IUiEffect
import com.indusjs.cm.app.viewmodels.mvi.IUiEvent
import com.indusjs.cm.app.viewmodels.mvi.IUiState
import com.indusjs.cm.domain.model.project.Projects


interface Tab1_ProjectsContract {
    sealed interface Event : IUiEvent {
        data object OnProjectsLoadFromRemote : Event
    }

    data class ProjectsState(
        val projects: ResourceUiState<Projects>
    ) : IUiState

    sealed interface Effect : IUiEffect {
        data object ShowProjectsOnUI : Effect
        data object IDLE : Effect
    }
}