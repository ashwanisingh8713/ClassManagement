package com.indusjs.cm.domain.usecase.login

import com.indusjs.cm.domain.model.login.SignInResponse
import com.indusjs.cm.domain.repo.login.ILoginRepo
import com.indusjs.cm.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher

class SignInUseCase(val loginRepo: ILoginRepo, dispatcher: CoroutineDispatcher):BaseUseCase<Any?, SignInResponse>(dispatcher) {

    override suspend fun block(param: Any?): SignInResponse {
        return loginRepo.signIn(param)
    }
}