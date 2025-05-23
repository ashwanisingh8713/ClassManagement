import kotlinx.serialization.Serializable

@Serializable
data class SignInResponse(
    val success: Boolean = true,
    val message: String = "",
    val redirectUrl: String? = null,
    val token: String? = null,
    val user: User? = null
)

@Serializable
data class User(
    val addressId: Int,
    val alternateCountryCode: Int,
    val alternateNumber: Long,
    val countryCode: Int,
    val email: String,
    val firstName: String,
    val isDeleted: Boolean,
    val isManagementCommittee: Boolean,
    val lastName: String,
    val livesHere: Boolean,
    val managementDesignation: String,
    val mobileNumber: String,
    val password: String,
    val photo: String? = null,
    val primaryContact: Boolean,
    val remarks: String? = null,
    val role: Role,
    val roleId: Int,
    val salutation: String? = null,
    val societyId: Int,
    val status: String? = null,
    val unitId: String? = null,
    val userId: Int
)

@Serializable
data class Role(
    val isDeleted: Boolean,
    val occupancyStatus: String? = null,
    val roleCategory: String? = null,
    val roleId: Int,
    val roleName: String? = null,
    val userGroupId: String? = null
)


