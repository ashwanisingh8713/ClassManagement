package com.indusjs.cm.repository

import com.indusjs.cm.domain.model.login.ForgotPasswordResponse
import com.indusjs.cm.domain.model.login.SignInResponse
import com.indusjs.cm.domain.model.login.SignupResponse
import com.indusjs.cm.domain.model.profile.ProfileResponse
import com.indusjs.cm.domain.repo.IUserRepository

class UserRepositoryImpl(private val loginRepo: ILoginRepo, private val profileRepo: IProfileRepo):IUserRepository {
    override suspend fun signIn(param: Any?): SignInResponse {
        return loginRepo.signIn(param)
    }

    override suspend fun signUp(param: Any?): SignupResponse {
        return loginRepo.signUp(param)
    }

    override suspend fun forgotPassword(param: Any?): ForgotPasswordResponse {
        return loginRepo.forgotPassword(param)
    }

    override suspend fun getProfile(param: Any?): ProfileResponse {
        return profileRepo.getProfile(param)
    }

    override suspend fun editProfile(param: Any?): ProfileResponse {
        return profileRepo.editProfile(param)
    }
}