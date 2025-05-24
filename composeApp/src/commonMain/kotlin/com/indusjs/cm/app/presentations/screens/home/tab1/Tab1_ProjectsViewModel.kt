package com.indusjs.cm.app.presentations.screens.home.tab1

import com.indusjs.cm.app.model.ResourceUiState
import com.indusjs.cm.app.viewmodels.mvi.MviBaseViewModel
import com.indusjs.cm.domain.usecase.projects.ProjectUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class Tab1_ProjectsViewModel(private val projectUseCase: ProjectUseCase,coroutineScope: CoroutineScope): MviBaseViewModel<Tab1_ProjectsContract.Event,
        Tab1_ProjectsContract.ProjectsState, Tab1_ProjectsContract.Effect>(coroutineScope) {

            init {
                // Initial load of projects can be triggered here if needed
                // setEvent(Tab1_ProjectsContract.Event.OnProjectsLoadFromRemote)
            }

    override fun createInitialState(): Tab1_ProjectsContract.ProjectsState {
        return Tab1_ProjectsContract.ProjectsState(
            projects = ResourceUiState.Loading
        )
    }

    override fun handleEvent(event: Tab1_ProjectsContract.Event) {
        when (event) {
            is Tab1_ProjectsContract.Event.OnProjectsLoadFromRemote -> {
                setState { copy(projects = ResourceUiState.Loading) }
                coroutineScope.launch {
                    projectUseCase(null).onSuccess { projects ->
                        setState { copy(projects = ResourceUiState.Success(projects)) }
                        //setEffect { Tab1_ProjectsContract.Effect.ShowProjectsOnUI(projects) }
                    }.onFailure { throwable ->
                        setState { copy(projects = ResourceUiState.Error(message = throwable.message ?: "Unknown error")) }
                    }
                }
            }
            else -> {
                // Handle other events if necessary
            }
        }
    }
}