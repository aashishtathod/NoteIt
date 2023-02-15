package dev.aashishtathod.noteit.domain.usecase

import dev.aashishtathod.noteit.core.utils.Either
import dev.aashishtathod.noteit.data.source.remote.dto.NoteResponse
import dev.aashishtathod.noteit.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetNoteUseCase @Inject constructor(
	private val noteRepository: NoteRepository
) {
	
	operator fun invoke(noteId: String): Flow<Either<NoteResponse>> = flow {
		val result = noteRepository.getNoteById(noteId)
		emit(result)
	}
}