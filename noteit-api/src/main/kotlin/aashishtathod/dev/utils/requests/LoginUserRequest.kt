package aashishtathod.dev.utils.requests

import kotlinx.serialization.Serializable

@Serializable
data class LoginUserRequest(
    val username: String,
    val password: String
)
