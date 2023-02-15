package dev.aashishtathod.noteit.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class NoteValidationUseCase @Inject constructor() {
	operator fun invoke(title: String, note: String): Flow<Boolean> = flow {
		val result = title.trim().length >= 4 && note.isNotBlank()
		emit(result)
	}
	
}