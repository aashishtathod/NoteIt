package dev.aashishtathod.noteit.ui.screens.addNote

import dev.aashishtathod.noteit.core.presentation.State

data class AddNoteState(
	val title: String = "",
	val note: String = "",
	val showSave: Boolean = false,
	val isAdding: Boolean = false,
	val added: Boolean = false,
	val errorMessage: String? = null
) : State