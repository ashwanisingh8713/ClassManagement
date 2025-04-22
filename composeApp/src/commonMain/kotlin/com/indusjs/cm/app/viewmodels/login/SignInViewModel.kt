package com.indusjs.cm.app.viewmodels.login


import androidx.lifecycle.ViewModel
import com.indusjs.cm.domain.usecase.login.SignInUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SignInViewModel(private val signInUseCase: SignInUseCase?): ViewModel() {

    val _successState = MutableStateFlow<String?>(null)
    val successState: StateFlow<String?> get() = _successState

    fun isValidEmail(email: String): Boolean {

        return true;
    }

    fun doLogin(): Unit {

    }




}