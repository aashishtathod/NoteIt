package dev.aashishtathod.noteit.ui.screens.notes

import androidx.compose.foundation.layout.Column
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import dev.aashishtathod.noteit.core.utils.NotyPreview
import dev.aashishtathod.noteit.core.utils.ext.collectState
import dev.aashishtathod.noteit.domain.model.Note
import dev.aashishtathod.noteit.ui.components.note.NotesList
import dev.aashishtathod.noteit.ui.components.scaffold.NoteItAppBar
import dev.aashishtathod.noteit.ui.components.scaffold.NoteItScaffold

@Composable
fun NotesScreen(
	viewModel: NotesViewModel,
	onNavigateToAddNote: () -> Unit,
	onNavigateToNoteDetail: (String) -> Unit,
	onNavigateToLogin: () -> Unit
) {
	val state by viewModel.collectState()
	
	val lifecycle = LocalLifecycleOwner.current.lifecycle
	val lifecycleEvent = remember { mutableStateOf(Lifecycle.Event.ON_ANY) }
	
	DisposableEffect(lifecycle) {
		val observer = LifecycleEventObserver { _, event ->
			lifecycleEvent.value = event
		}
		lifecycle.addObserver(observer)
		onDispose {
			lifecycle.removeObserver(observer)
		}
	}
	
	if (lifecycleEvent.value == Lifecycle.Event.ON_RESUME) {
		LaunchedEffect(lifecycleEvent) {
			viewModel.getNotes()
		}
	}
	
	NotesContent(
		isLoading = state.isLoading,
		notes = state.notes,
		//	isConnectivityAvailable = state.isConnectivityAvailable,
		onAddNoteClick = onNavigateToAddNote,
		//	onLogoutClick = { showLogoutConfirmation = true },
		onNavigateToNoteDetail = onNavigateToNoteDetail
	)
	
	val isUserLoggedIn = state.isUserLoggedIn
	
	LaunchedEffect(isUserLoggedIn) {
		if (isUserLoggedIn == false) {
			onNavigateToLogin()
		}
	}
}

@Composable
fun NotesContent(
	isLoading: Boolean,
	notes: List<Note>,
//	isConnectivityAvailable: Boolean?, Todo
	error: String? = null,
	onAddNoteClick: () -> Unit,
//	onLogoutClick: () -> Unit,      Todo
	onNavigateToNoteDetail: (String) -> Unit
) {
	
	NoteItScaffold(
		isLoading = isLoading,
		error = error,
		appBar = {
			NoteItAppBar(
				title = "Notes",
				actions = {
					// LogoutAction(onLogout = onLogoutClick)
				}
			)
		},
		content = {
			Column {
				NotesList(
					notes = notes,
					onClick = { note ->
						onNavigateToNoteDetail(note.noteId.toString())
					}
				)
			}
		},
		
		floatingActionButton = {
			FloatingActionButton(
				onClick = onAddNoteClick,
				backgroundColor = MaterialTheme.colors.primary
			) {
				Icon(
					Icons.Filled.Add,
					"Add",
					tint = Color.White
				)
			}
		}
	)
}

@Preview
@Composable
fun NotesScreenPreview() = NotyPreview {
	NotesContent(
		isLoading = false,
		notes = emptyList(),
		onAddNoteClick = { },
		onNavigateToNoteDetail = {}
	)
}
