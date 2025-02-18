package com.indusjs.cm.domain.usecase.login

import com.indusjs.cm.domain.model.login.LoginResponse
import com.indusjs.cm.domain.repo.login.ILoginRepo
import com.indusjs.cm.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher

class LoginUseCase(val loginRepo: ILoginRepo, dispatcher: CoroutineDispatcher):BaseUseCase<Any?, LoginResponse>(dispatcher) {

    override suspend fun block(param: Any?): LoginResponse {
        return loginRepo.signIn(param)
    }
}