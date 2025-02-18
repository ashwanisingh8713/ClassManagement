package com.indusjs.cm.domain.usecase.login

import com.indusjs.cm.domain.model.login.SignupResponse
import com.indusjs.cm.domain.repo.login.ILoginRepo
import com.indusjs.cm.domain.repo.profile.IProfileRepo
import com.indusjs.cm.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher

class SignUpUseCase(private val loginRepo: ILoginRepo, dispatcher: CoroutineDispatcher): BaseUseCase<Any?, SignupResponse>(dispatcher) {
    override suspend fun block(param: Any?): SignupResponse {
        return loginRepo.signUp(param)
    }

}