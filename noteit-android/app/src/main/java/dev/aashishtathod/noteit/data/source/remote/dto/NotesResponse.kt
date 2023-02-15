package dev.aashishtathod.noteit.data.source.remote.dto

import dev.aashishtathod.noteit.core.data.BaseResponse
import dev.aashishtathod.noteit.domain.model.Note

data class NotesResponse(
	override val status: Int,
	override val message: String,
	val notes: List<Note>? = null
) : BaseResponse

