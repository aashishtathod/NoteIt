package dev.aashishtathod.noteit.data.source.remote.api

import dev.aashishtathod.noteit.data.source.remote.dto.NoteResponse
import dev.aashishtathod.noteit.data.source.remote.dto.NotesResponse
import dev.aashishtathod.noteit.data.source.remote.request.NoteRequest
import dev.aashishtathod.noteit.data.source.remote.request.NoteUpdatePinRequest
import retrofit2.Response
import retrofit2.http.*

interface NoteService {
	
	@GET("notes")
	suspend fun getAllNotes(): NotesResponse
	
	@POST("note/new")
	suspend fun addNote(
		@Body request: NoteRequest
	): NoteResponse
	
	@GET("note/{noteId}")
	suspend fun getNoteById(
		@Path("noteId") noteId: Int
	): NoteResponse
	
	
	@PUT("/note/{noteId}")
	suspend fun updateNote(
		@Path("noteId") noteId: Int,
		@Body noteRequest: NoteRequest
	): NoteResponse
	
	@DELETE("/note/{noteId}")
	suspend fun deleteNote(
		@Path("noteId") noteId: Int
	): NoteResponse
	
	@PATCH("/note/{noteId}/pin")
	suspend fun updateNotePin(
		@Path("noteId") noteId: Int,
		@Body noteRequest: NoteUpdatePinRequest
	): NoteResponse
}