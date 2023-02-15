package aashishtathod.dev.entity

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val name: String,
    val userName: String,
    val password: String,
    val userId: Int
)
