package com.indusjs.cm.app.viewmodels.profile

import androidx.lifecycle.ViewModel
import com.indusjs.cm.domain.usecase.profile.EditProfileUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class EditProfileViewModel(private val editProfileUseCase: EditProfileUseCase):ViewModel() {
    private val _successState: MutableStateFlow<String> = MutableStateFlow("")
    val uiState: StateFlow<String> = _successState.asStateFlow()
}