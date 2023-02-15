package dev.aashishtathod.noteit.ui.screens.noteDetail

import dev.aashishtathod.noteit.core.presentation.State

data class NoteDetailState(
	val isLoading: Boolean = false,
	val title: String = "",
	val note: String = "",
	val isPinned: Boolean = false,
	val showSave: Boolean = false,
	val finished: Boolean = false,
	val error: String? = null
) : State
