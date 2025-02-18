package com.indusjs.cm.domain.repo.login

import com.indusjs.cm.domain.model.login.ForgotPasswordResponse
import com.indusjs.cm.domain.model.login.LoginResponse
import com.indusjs.cm.domain.model.login.SignupResponse

interface ILoginRepo {
    suspend fun signIn(param: Any?): LoginResponse
    suspend fun signUp(param: Any?):SignupResponse
    suspend fun forgotPassword(param: Any?):ForgotPasswordResponse
}