package aashishtathod.dev.utils.responses

import aashishtathod.dev.entity.User
import io.ktor.http.*
import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    override val status: Int,
    override val message: String,
    val token: String? = null
) : BaseResponse {

    companion object {

        fun failed(message: String) = AuthResponse(
            State.FAILED.value,
            message
        )

        fun unauthorized(message: String) = AuthResponse(
            State.UNAUTHORIZED.value,
            message
        )

        fun success(token: String, message: String) = AuthResponse(
            State.SUCCESS.value,
            message,
            token
        )
    }
}