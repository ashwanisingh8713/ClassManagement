package com.indusjs.cm.domain.model.login

import kotlinx.serialization.Serializable

@Serializable
data class UserProfileResponse(
    val client: Client,
    val message: String
)


@Serializable
data class Client(
    val address: String,
    val clientId: String,
    val clientName: String,
    val company: String,
    val contactNumber: String,
    val email: String,
    val projects: List<String>
)





