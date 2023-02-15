package aashishtathod.dev.utils.requests

import kotlinx.serialization.Serializable

@Serializable
data class RegisterUserRequest(
    val username: String,
    val password: String,
    val name: String
)