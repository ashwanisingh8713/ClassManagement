package com.indusjs.cm.app.viewmodels.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ForgotPasswordViewModel: ViewModel() {

    val _successState = MutableStateFlow<String?>(null)
    val successState: StateFlow<String?> get() = _successState

    fun resetForgotPassword() {

    }



}