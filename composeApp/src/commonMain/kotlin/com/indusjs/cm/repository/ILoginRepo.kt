package com.indusjs.cm.repository

import com.indusjs.cm.domain.model.login.ForgotPasswordResponse
import com.indusjs.cm.domain.model.login.SignInResponse
import com.indusjs.cm.domain.model.login.SignupResponse

interface ILoginRepo {
    suspend fun signIn(param: Any?): SignInResponse
    suspend fun signUp(param: Any?):SignupResponse
    suspend fun forgotPassword(param: Any?):ForgotPasswordResponse
}