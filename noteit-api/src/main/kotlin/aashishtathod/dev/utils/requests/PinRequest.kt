package aashishtathod.dev.utils.requests

import kotlinx.serialization.Serializable

@Serializable
data class PinRequest(
    val isPinned: Boolean
)