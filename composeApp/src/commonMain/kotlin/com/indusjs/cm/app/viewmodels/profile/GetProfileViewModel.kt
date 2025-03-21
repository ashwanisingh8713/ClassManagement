package com.indusjs.cm.app.viewmodels.profile

import androidx.lifecycle.ViewModel
import com.indusjs.cm.domain.usecase.profile.GetProfileUseCase

class GetProfileViewModel(private val getProfileUseCase: GetProfileUseCase): ViewModel() {
}