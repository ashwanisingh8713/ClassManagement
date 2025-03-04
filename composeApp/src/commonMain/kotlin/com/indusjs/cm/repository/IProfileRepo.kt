package com.indusjs.cm.repository

import com.indusjs.cm.domain.model.profile.ProfileResponse

interface IProfileRepo {
    suspend fun getProfile(param: Any?): ProfileResponse
    suspend fun editProfile(param: Any?): ProfileResponse
}