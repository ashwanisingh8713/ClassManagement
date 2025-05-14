package com.indusjs.cm.domain.repo

import SignInResponse
import com.indusjs.cm.domain.model.login.ForgotPasswordResponse
import com.indusjs.cm.domain.model.login.SignUpResponse
import com.indusjs.cm.domain.model.profile.ProfileResponse

interface IUserRepository {
    
    suspend fun signIn(param: Any?): SignInResponse
    suspend fun signUp(param: Any?): SignUpResponse
    suspend fun forgotPassword(param: Any?): ForgotPasswordResponse

    suspend fun getProfile(param: Any?): ProfileResponse
    suspend fun editProfile(param: Any?): ProfileResponse
}