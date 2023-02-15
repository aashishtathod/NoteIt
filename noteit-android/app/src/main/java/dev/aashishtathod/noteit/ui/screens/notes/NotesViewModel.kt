package dev.aashishtathod.noteit.ui.screens.notes

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.aashishtathod.noteit.core.presentation.BaseViewModel
import dev.aashishtathod.noteit.core.utils.Either
import dev.aashishtathod.noteit.domain.usecase.GetAllNotesUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
	private val getAllNotesUseCase: GetAllNotesUseCase
) : BaseViewModel<NotesState>(NotesState()) {
	
	 fun getNotes() {
		setState { state -> state.copy(isLoading = true) }
		viewModelScope.launch {
			getAllNotesUseCase().collect {
				when (it) {
					is Either.Success -> setState { state ->
						state.copy(isLoading = false, notes = it.data.notes ?: emptyList())
					}
					
					is Either.Error -> setState { state ->
						state.copy(isLoading = false, error = it.message)
					}
					
				}
			}
		}
		
	}
}
