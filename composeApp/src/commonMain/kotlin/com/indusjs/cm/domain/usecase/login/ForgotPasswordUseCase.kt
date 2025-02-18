package com.indusjs.cm.domain.usecase.login

import com.indusjs.cm.domain.model.login.ForgotPasswordResponse
import com.indusjs.cm.domain.repo.login.ILoginRepo
import com.indusjs.cm.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher

class ForgotPasswordUseCase(private val loginRepo: ILoginRepo, dispatcher: CoroutineDispatcher): BaseUseCase<Any?, ForgotPasswordResponse>(dispatcher) {

    override suspend fun block(param: Any?): ForgotPasswordResponse {
        return loginRepo.forgotPassword(param)
    }

}