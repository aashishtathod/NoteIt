package aashishtathod.dev.controllers

import aashishtathod.dev.data.dao.NoteDao
import aashishtathod.dev.entity.Note
import aashishtathod.dev.entity.User
import aashishtathod.dev.utils.exceptions.NoteNotFoundException
import aashishtathod.dev.utils.exceptions.UnauthorizedActivityException
import aashishtathod.dev.utils.requests.NoteRequest
import aashishtathod.dev.utils.requests.PinRequest
import aashishtathod.dev.utils.responses.NoteResponse
import aashishtathod.dev.utils.responses.NotesResponse
import io.ktor.server.plugins.*

class NoteController(
    private val noteDao: NoteDao
) {

    suspend fun getNotesByUser(user: User): NotesResponse {
        return try {
            val notes = noteDao.getAllByUser(user.userId)

            NotesResponse.success(
                notes.map { Note(it.noteId, it.userId, it.title, it.note, it.createdAt, it.isPinned, it.updatedAt) }
            )
        } catch (uae: UnauthorizedActivityException) {
            NotesResponse.unauthorized(uae.message)
        }
    }

    suspend fun getNoteById(noteId: Int): NoteResponse {
        return try {
            val note = noteDao.getById(noteId)

            if (note == null) {
                NoteResponse.failed("Error Occurred")
            } else {
                NoteResponse.success(note)
            }

        } catch (uae: UnauthorizedActivityException) {
            NoteResponse.unauthorized(uae.message)
        }
    }


    suspend fun addNote(user: User, note: NoteRequest): NoteResponse {
        return try {
            val noteTitle = note.title.trim()
            val noteText = note.note.trim()

            validateNoteOrThrowException(noteTitle, noteText)

            val noteId = noteDao.add(user.userId, noteTitle, noteText)
            if (noteId == null) {
                NoteResponse.failed("Error Occurred")
            } else {
                NoteResponse.success()

            }
        } catch (bre: BadRequestException) {
            NoteResponse.failed(bre.message!!)
        }
    }

    suspend fun updateNote(user: User, noteId: Int, note: NoteRequest): NoteResponse {
        return try {
            val noteTitle = note.title.trim()
            val noteText = note.note.trim()

            validateNoteOrThrowException(noteTitle, noteText)
            checkNoteExistsOrThrowException(noteId)
            checkOwnerOrThrowException(user.userId, noteId)

            if (noteDao.update(noteId, noteTitle, noteText)) {
                NoteResponse.success()
            } else {
                NoteResponse.failed("Error Occurred")
            }

        } catch (uae: UnauthorizedActivityException) {
            NoteResponse.unauthorized(uae.message)
        } catch (bre: BadRequestException) {
            NoteResponse.failed(bre.message!!)
        } catch (nfe: NoteNotFoundException) {
            NoteResponse.notFound(nfe.message)
        }
    }


    suspend fun deleteNote(user: User, noteId: Int): NoteResponse {
        return try {
            checkNoteExistsOrThrowException(noteId)
            checkOwnerOrThrowException(user.userId, noteId)

            if (noteDao.deleteById(noteId)) {
                NoteResponse.success()
            } else {
                NoteResponse.failed("Error Occurred")
            }

        } catch (uae: UnauthorizedActivityException) {
            NoteResponse.unauthorized(uae.message)
        } catch (bre: BadRequestException) {
            NoteResponse.failed(bre.message!!)
        } catch (nfe: NoteNotFoundException) {
            NoteResponse.notFound(nfe.message)
        }
    }


    suspend fun updateNotePin(user: User, noteId: Int, pinRequest: PinRequest): NoteResponse {
        return try {
            checkNoteExistsOrThrowException(noteId)
            checkOwnerOrThrowException(user.userId, noteId)

            if (noteDao.updateNotePinById(noteId, pinRequest.isPinned)) {
                NoteResponse.success()
            } else {
                NoteResponse.failed("Error Occurred")
            }
        } catch (uae: UnauthorizedActivityException) {
            NoteResponse.unauthorized(uae.message)
        } catch (bre: BadRequestException) {
            NoteResponse.failed(bre.message!!)
        } catch (nfe: NoteNotFoundException) {
            NoteResponse.notFound(nfe.message)
        }
    }


    private fun validateNoteOrThrowException(title: String, note: String) {
        val message = when {
            (title.isBlank() or note.isBlank()) -> "Title and Note should not be blank"
            (title.length !in (4..30)) -> "Title should be of min 4 and max 30 character in length"
            else -> return
        }

        throw BadRequestException(message)
    }

    private suspend fun checkNoteExistsOrThrowException(noteId: Int) {
        if (!noteDao.exists(noteId)) {
            throw NoteNotFoundException("Note not exist with ID '$noteId'")
        }
    }

    private suspend fun checkOwnerOrThrowException(userId: Int, noteId: Int) {
        if (!noteDao.isNoteOwnedByUser(noteId, userId)) {
            throw UnauthorizedActivityException("Access denied")
        }
    }

}