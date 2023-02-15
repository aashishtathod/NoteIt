package dev.aashishtathod.noteit.ui.screens.noteDetail

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.aashishtathod.noteit.core.presentation.BaseViewModel
import dev.aashishtathod.noteit.core.utils.Either
import dev.aashishtathod.noteit.domain.model.Note
import dev.aashishtathod.noteit.domain.usecase.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
	private val noteValidationUseCase: NoteValidationUseCase,
	private val getNoteUseCase: GetNoteUseCase,
	private val updateNoteUseCase: UpdateNoteUseCase,
	private val updateNotePinUseCase: UpdateNotePinUseCase,
	private val deleteNoteUseCase: DeleteNoteUseCase
) : BaseViewModel<NoteDetailState>(NoteDetailState()) {
	
	private lateinit var currentNote: Note
	
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
	
	fun loadNote(noteId: String) {
		setState { state -> state.copy(isLoading = true) }
		viewModelScope.launch {
			getNoteUseCase(noteId).collect {
				when (it) {
					is Either.Success -> {
						currentNote = it.data.note!!
						
						setState { state ->
							state.copy(
								isLoading = false,
								title = currentNote.title,
								note = currentNote.note,
								isPinned = currentNote.isPinned
							)
						}
					}
					
					is Either.Error -> setState { state ->
						state.copy(isLoading = false, error = it.message)
					}
					
				}
			}
		}
	}
	
	fun save() {
		val title = currentState.title.trim()
		val note = currentState.note.trim()
		
		setState { state -> state.copy(isLoading = true) }
		viewModelScope.launch {
			updateNoteUseCase(currentNote.noteId, title, note).collect {
				when (it) {
					is Either.Success -> setState { state ->
						state.copy(
							isLoading = false,
							finished = true
						)
					}
					is Either.Error -> setState { state ->
						state.copy(
							isLoading = false,
							error = it.message
						)
					}
				}
			}
		}
	}
	
	fun togglePin() {
		setState { state -> state.copy(isLoading = true) }
		viewModelScope.launch {
			updateNotePinUseCase(currentNote.noteId, !currentState.isPinned).collect {
				when (it) {
					is Either.Success -> setState { state ->
						state.copy(isLoading = false, isPinned = !currentState.isPinned)
					}
					
					is Either.Error -> setState { state ->
						state.copy(isLoading = false, error = it.message)
					}
					
				}
			}
		}
	}
	
	fun deleteNote(){
		setState { state -> state.copy(isLoading = true) }
		viewModelScope.launch {
			deleteNoteUseCase(currentNote.noteId).collect {
				when (it) {
					is Either.Success -> setState { state ->
						state.copy(isLoading = false, finished = true)
					}
					
					is Either.Error -> setState { state ->
						state.copy(isLoading = false, error = it.message)
					}
					
				}
			}
		}
	}
	
	
}