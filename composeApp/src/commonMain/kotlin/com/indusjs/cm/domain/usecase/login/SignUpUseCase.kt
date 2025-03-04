package com.indusjs.cm.domain.usecase.login

import com.indusjs.cm.domain.model.login.SignupResponse
import com.indusjs.cm.domain.repo.IUserRepository
import com.indusjs.cm.repository.ILoginRepo
import com.indusjs.cm.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher

class SignUpUseCase(private val repository: IUserRepository, dispatcher: CoroutineDispatcher): BaseUseCase<Any?, SignupResponse>(dispatcher) {
    override suspend fun block(param: Any?): SignupResponse {
        return repository.signUp(param)
    }

}