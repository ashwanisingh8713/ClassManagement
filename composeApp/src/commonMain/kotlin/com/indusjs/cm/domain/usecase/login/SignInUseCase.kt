package com.indusjs.cm.domain.usecase.login

import SignInResponse
import com.indusjs.cm.domain.repo.IUserRepository
import com.indusjs.cm.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher

class SignInUseCase(private val repository: IUserRepository, dispatcher: CoroutineDispatcher):BaseUseCase<Any?, SignInResponse>(dispatcher) {

    override suspend fun block(param: Any?): SignInResponse {
        return repository.signIn(param)
    }
}