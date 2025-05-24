import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignInResponse(
    val success: Boolean = true,
    val message: String = "",
    @SerialName("data")
    val `data`: Data? = null,
    @SerialName("profile")
    val profile: UserProfile? = null
)

@Serializable
data class Data(
    @SerialName("email")
    val email: String,
    @SerialName("message")
    val message: String,
    @SerialName("password")
    val password: String,
    @SerialName("role")
    val role: String,
    @SerialName("status")
    val status: Boolean,
    @SerialName("token")
    val token: String
)

@Serializable
data class UserProfile(
    @SerialName("address")
    val address: String,
    @SerialName("available")
    val available: String,
    @SerialName("client_id")
    val clientId: String,
    @SerialName("name")
    val name: String,
    @SerialName("contact_email")
    val contactEmail: String,
    @SerialName("email")
    val email: String,
    @SerialName("experience")
    val experience: Int,
    @SerialName("linkedin")
    val linkedin: String,
    @SerialName("projects")
    val projects: List<String>? = null,
    @SerialName("skills")
    val skills: List<String>? = null,
)







