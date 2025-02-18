package com.indusjs.cm.domain.usecase.profile

import com.indusjs.cm.domain.model.profile.ProfileResponse
import com.indusjs.cm.domain.repo.profile.IProfileRepo
import com.indusjs.cm.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher

class GetProfileUseCase(private val profileRepo: IProfileRepo, dispatcher: CoroutineDispatcher):BaseUseCase<Any?, ProfileResponse>(dispatcher) {

    override suspend fun block(param: Any?): ProfileResponse {
        return profileRepo.getProfile(param)
    }

}