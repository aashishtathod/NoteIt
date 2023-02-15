package dev.aashishtathod.noteit.domain.repository

import dev.aashishtathod.noteit.core.utils.Either
import dev.aashishtathod.noteit.data.source.remote.dto.NoteResponse
import dev.aashishtathod.noteit.data.source.remote.dto.NotesResponse

interface NoteRepository {
	suspend fun getNoteById(noteId: String): Either<NoteResponse>
	
	suspend fun getAllNotes(): Either<NotesResponse>
	
	suspend fun addNote(title: String, note: String): Either<NoteResponse>
	
	suspend fun updateNote(
		noteId: Int,
		title: String,
		note: String
	): Either<NoteResponse>
	
	suspend fun deleteNote(noteId: Int): Either<NoteResponse>
	
	suspend fun updateNotePin(noteId: Int, isPinned: Boolean): Either<NoteResponse>

//	suspend fun deleteAllNotes()
	
}