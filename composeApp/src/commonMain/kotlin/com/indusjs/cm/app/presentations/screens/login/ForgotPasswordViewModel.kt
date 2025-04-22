package com.indusjs.cm.app.presentations.screens.login

import com.indusjs.cm.app.presentations.mvi.MviBaseViewModel
import kotlinx.coroutines.CoroutineScope

class ForgotPasswordViewModel(coroutineScope: CoroutineScope):MviBaseViewModel<LoginContract.Event, LoginContract.SignInState, LoginContract.Effect>(coroutineScope) {
    override fun createInitialState(): LoginContract.SignInState {
        TODO("Not yet implemented")
    }

    override fun handleEvent(event: LoginContract.Event) {
        TODO("Not yet implemented")
    }

}