package aashishtathod.dev.data.dao

import aashishtathod.dev.entity.Note

interface NoteDao {
    suspend fun add(userId: Int, title: String, note: String): Int?
    suspend fun getAllByUser(userId: Int): List<Note>
    suspend fun getById(noteId: Int): Note?
    suspend fun update(noteId: Int, title: String, note: String): Boolean
    suspend fun deleteById(noteId: Int): Boolean
    suspend fun isNoteOwnedByUser(noteId: Int, userId: Int): Boolean
    suspend fun exists(noteId: Int): Boolean
    suspend fun updateNotePinById(noteId: Int, isPinned: Boolean): Boolean
}