package com.indusjs.cm.domain.usecase.login

import SignInResponse
import com.indusjs.cm.domain.model.login.SignUpResponse
import com.indusjs.cm.domain.repo.IUserRepository
import com.indusjs.cm.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher

class SignUpUseCase(private val repository: IUserRepository, dispatcher: CoroutineDispatcher): BaseUseCase<Any?, SignUpResponse>(dispatcher) {
    override suspend fun block(param: Any?): SignUpResponse {
        return repository.signUp(param)
    }

}