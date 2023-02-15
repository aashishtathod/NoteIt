package aashishtathod.dev.entity

import kotlinx.serialization.Serializable
import org.joda.time.DateTime

@Serializable
data class Note(
    val noteId: Int,
    val userId: Int,
    val title: String,
    val note: String,
    val createdAt: String,
    val isPinned: Boolean,
    val updatedAt: String
)
