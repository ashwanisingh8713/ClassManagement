package com.indusjs.cm.app.presentations.screens.login

import com.indusjs.cm.app.presentations.mvi.MviBaseViewModel
import kotlinx.coroutines.CoroutineScope

class ForgotPasswordViewModel(coroutineScope: CoroutineScope):MviBaseViewModel<LoginContract.Event, LoginContract.State, LoginContract.Effect>(coroutineScope) {
    override fun createInitialState(): LoginContract.State {
        TODO("Not yet implemented")
    }

    override fun handleEvent(event: LoginContract.Event) {
        TODO("Not yet implemented")
    }

}