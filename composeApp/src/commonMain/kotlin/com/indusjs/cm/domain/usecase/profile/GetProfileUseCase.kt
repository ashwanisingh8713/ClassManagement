package com.indusjs.cm.domain.usecase.profile

import com.indusjs.cm.domain.model.profile.ProfileResponse
import com.indusjs.cm.domain.repo.IUserRepository
import com.indusjs.cm.repository.IProfileRepo
import com.indusjs.cm.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher

class GetProfileUseCase(private val repository: IUserRepository, dispatcher: CoroutineDispatcher):BaseUseCase<Any?, ProfileResponse>(dispatcher) {

    override suspend fun block(param: Any?): ProfileResponse {
        return repository.getProfile(param)
    }

}