package dev.aashishtathod.noteit.domain.usecase

import dev.aashishtathod.noteit.core.utils.Either
import dev.aashishtathod.noteit.data.source.remote.dto.NotesResponse
import dev.aashishtathod.noteit.domain.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetAllNotesUseCase @Inject constructor(
	private val noteRepository: NoteRepository
) {
	operator fun invoke(): Flow<Either<NotesResponse>> = flow {
		val result = noteRepository.getAllNotes()
		emit(result)
	}.flowOn(Dispatchers.IO)
}