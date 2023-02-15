package dev.aashishtathod.noteit.ui.screens.notes

import dev.aashishtathod.noteit.core.presentation.State
import dev.aashishtathod.noteit.domain.model.Note

data class NotesState(
    val isLoading: Boolean = false,
    val notes: List<Note> = emptyList(),
    val error: String? = null,
    val isUserLoggedIn: Boolean? = null,
    val isConnectivityAvailable: Boolean? = null
) : State
