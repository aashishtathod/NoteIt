package aashishtathod.dev.utils.responses

import kotlinx.serialization.Serializable

@Serializable
data class FailureResponse(override val status: Int, override val message: String) : BaseResponse