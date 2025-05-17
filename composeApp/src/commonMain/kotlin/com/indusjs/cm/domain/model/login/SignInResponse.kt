import kotlinx.serialization.Serializable

@Serializable
data class SignInResponse(
    val success: Boolean = true,
    val message: String = "",
    val `data`: Data? = null
)


@Serializable
data class Data(
    val email: String,
    val message: String,
    val password: String,
    val status: Boolean,
    val token: String
)

