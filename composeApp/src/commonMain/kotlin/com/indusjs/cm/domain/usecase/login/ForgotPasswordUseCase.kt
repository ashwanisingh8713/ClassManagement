package com.indusjs.cm.domain.usecase.login

import com.indusjs.cm.domain.model.login.ForgotPasswordResponse
import com.indusjs.cm.domain.repo.IUserRepository
import com.indusjs.cm.repository.ILoginRepo
import com.indusjs.cm.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher

class ForgotPasswordUseCase(private val repository: IUserRepository, dispatcher: CoroutineDispatcher):
    BaseUseCase<Any?, ForgotPasswordResponse>(dispatcher) {

    override suspend fun block(param: Any?): ForgotPasswordResponse {
        return repository.forgotPassword(param)
    }

}