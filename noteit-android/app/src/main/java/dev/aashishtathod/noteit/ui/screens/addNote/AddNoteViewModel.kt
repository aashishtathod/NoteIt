package dev.aashishtathod.noteit.ui.screens.addNote

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.aashishtathod.noteit.core.presentation.BaseViewModel
import dev.aashishtathod.noteit.core.utils.Either
import dev.aashishtathod.noteit.domain.usecase.AddNoteUseCase
import dev.aashishtathod.noteit.domain.usecase.NoteValidationUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(
	private val addNoteUseCase: AddNoteUseCase,
	private val noteValidationUseCase: NoteValidationUseCase
) : BaseViewModel<AddNoteState>(AddNoteState()) {
	
	fun setTitle(title: String) {
		setState { state -> state.copy(title = title) }
		validateNote()
	}
	
	fun setNote(note: String) {
		setState { state -> state.copy(note = note) }
		validateNote()
	}
	
	private fun validateNote() {
		viewModelScope.launch {
			val title = currentState.title
			val note = currentState.note
			noteValidationUseCase(title, note).collect {
				setState { state -> state.copy(showSave = it) }
			}
		}
	}
	
	fun add() {
		setState { state -> state.copy(isAdding = true) }
		viewModelScope.launch {
			val title = currentState.title
			val note = currentState.note
			addNoteUseCase(title, note).collect {
				when (it) {
					is Either.Success -> setState { state ->
						state.copy(
							isAdding = false,
							added = true
						)
					}
					
					is Either.Error -> setState { state ->
						state.copy(
							isAdding = false,
							errorMessage = it.message
						)
					}
					
				}
			}
		}
		
	}
	
}