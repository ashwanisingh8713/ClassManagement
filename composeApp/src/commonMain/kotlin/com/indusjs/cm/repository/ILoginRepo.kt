package com.indusjs.cm.repository

import SignInResponse
import com.indusjs.cm.domain.model.login.ForgotPasswordResponse
import com.indusjs.cm.domain.model.login.SignUpResponse

interface ILoginRepo {
    suspend fun signIn(param: Any?): SignInResponse
    suspend fun signUp(param: Any?): SignUpResponse
    suspend fun forgotPassword(param: Any?):ForgotPasswordResponse
}