package dev.aashishtathod.noteit.data.repositoryImpl

import dev.aashishtathod.noteit.core.utils.Either
import dev.aashishtathod.noteit.data.source.remote.dataSource.NoteRemoteDataSource
import dev.aashishtathod.noteit.data.source.remote.dto.NoteResponse
import dev.aashishtathod.noteit.data.source.remote.dto.NotesResponse
import dev.aashishtathod.noteit.data.source.remote.request.NoteRequest
import dev.aashishtathod.noteit.data.source.remote.request.NoteUpdatePinRequest
import dev.aashishtathod.noteit.domain.repository.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
	private val noteRemoteDataSource: NoteRemoteDataSource
) : NoteRepository {
	
	override suspend fun getNoteById(noteId: String): Either<NoteResponse> {
		return noteRemoteDataSource.getNoteById(noteId)
	}
	
	override suspend fun getAllNotes(): Either<NotesResponse> {
		return noteRemoteDataSource.getAllNotes()
	}
	
	override suspend fun addNote(title: String, note: String): Either<NoteResponse> {
		return noteRemoteDataSource.addNote(NoteRequest(title, note))
	}
	
	override suspend fun updateNote(
		noteId: Int,
		title: String,
		note: String
	): Either<NoteResponse> {
		return noteRemoteDataSource.updateNote(noteId, NoteRequest(title, note))
	}
	
	override suspend fun deleteNote(noteId: Int): Either<NoteResponse> {
		return noteRemoteDataSource.deleteNote(noteId)
	}
	
	override suspend fun updateNotePin(noteId: Int, isPinned: Boolean): Either<NoteResponse> {
		return noteRemoteDataSource.updateNotePin(noteId, NoteUpdatePinRequest(isPinned))
	}
	
}
