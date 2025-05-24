package com.indusjs.cm.data.repo

import SignInResponse
import com.indusjs.cm.domain.model.login.ForgotPasswordResponse
import com.indusjs.cm.domain.model.login.SignUpResponse
import com.indusjs.cm.repository.ILoginRepo
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import kotlinx.serialization.Serializable

class LoginRepoImpl(private val endPoint: String,
                    private val httpClient: HttpClient):ILoginRepo {
    override suspend fun signIn(param: Any?): SignInResponse {
        val bodyParam = param as? LoginRequestBody
        val response: HttpResponse = httpClient.post() {
            url("$endPoint/api/auth/login")
            setBody(bodyParam)
            contentType(ContentType.Application.Json)
        }
        return if(response.status.isSuccess()) {
            response.body<SignInResponse>()
        } else {
            SignInResponse(
                success = false,
                message = response.status.toString(),
            )
        }
    }

    override suspend fun signUp(param: Any?): SignUpResponse {
        TODO("Not yet implemented")
    }

    override suspend fun forgotPassword(param: Any?): ForgotPasswordResponse {
        TODO("Not yet implemented")
    }
}

@Serializable
data class LoginRequestBody(
    val email: String,
    val password: String,
    val userType:String = UserType.None().type,
)

@Serializable
sealed class  UserType {
    data class Security(val type:String = "security") : UserType()
    data class Residential(val type:String = "residential") : UserType()
    data class None(val type:String = "None") : UserType()
}