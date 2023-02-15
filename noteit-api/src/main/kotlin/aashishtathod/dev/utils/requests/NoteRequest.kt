package aashishtathod.dev.utils.requests

import kotlinx.serialization.Serializable

@Serializable
data class NoteRequest(
    val title: String,
    val note: String
)
