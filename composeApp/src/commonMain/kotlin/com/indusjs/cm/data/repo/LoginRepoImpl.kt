package com.indusjs.cm.data.repo

import com.indusjs.cm.domain.model.login.ForgotPasswordResponse
import com.indusjs.cm.domain.model.login.SignInResponse
import com.indusjs.cm.domain.model.login.SignupResponse
import com.indusjs.cm.repository.ILoginRepo
import io.ktor.client.HttpClient

class LoginRepoImpl(private val endPoint: String,
                    private val httpClient: HttpClient):ILoginRepo {
    override suspend fun signIn(param: Any?): SignInResponse {
        TODO("Not yet implemented")
    }

    override suspend fun signUp(param: Any?): SignupResponse {
        TODO("Not yet implemented")
    }

    override suspend fun forgotPassword(param: Any?): ForgotPasswordResponse {
        TODO("Not yet implemented")
    }
}