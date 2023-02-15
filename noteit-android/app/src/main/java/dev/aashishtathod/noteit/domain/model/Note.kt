package dev.aashishtathod.noteit.domain.model

data class Note(
	val noteId: Int,
	val title: String,
	val note: String,
	val createdAt: String,
	val isPinned: Boolean = false
)

