package dev.aashishtathod.noteit.ui.screens.noteDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.aashishtathod.noteit.core.utils.ext.collectState
import dev.aashishtathod.noteit.ui.components.actions.DeleteAction
import dev.aashishtathod.noteit.ui.components.actions.PinAction
import dev.aashishtathod.noteit.ui.components.dialog.ConfirmationDialog
import dev.aashishtathod.noteit.ui.components.dialog.LoaderDialog
import dev.aashishtathod.noteit.ui.components.scaffold.NoteItAppBar
import dev.aashishtathod.noteit.ui.components.scaffold.NoteItScaffold
import dev.shreyaspatil.noty.composeapp.component.text.NoteField
import dev.shreyaspatil.noty.composeapp.component.text.NoteTitleField

@Composable
fun NoteDetailsScreen(
	noteId: String,
	viewModel: NoteDetailViewModel,
	onNavigateUp: () -> Unit
) {
	
	LaunchedEffect(key1 = Unit) {
		viewModel.loadNote(noteId)
	}
	
	val state by viewModel.collectState()
	
	var showDeleteNoteConfirmation by remember { mutableStateOf(false) }
	
	NoteDetailContent(
		isLoading = state.isLoading,
		title = state.title,
		note = state.note,
		error = state.error,
		isPinned = state.isPinned,
		showSaveButton = state.showSave,
		onTitleChange = viewModel::setTitle,
		onNoteChange = viewModel::setNote,
		onPinClick = { viewModel.togglePin() },
		onSaveClick = { viewModel.save() },
		onDeleteClick = { showDeleteNoteConfirmation = true },
		onNavigateUp = onNavigateUp,
	)
	
	DeleteNoteConfirmation(
		show = showDeleteNoteConfirmation,
		onConfirm = { viewModel.deleteNote() },
		onDismiss = { showDeleteNoteConfirmation = false }
	)
	
	LaunchedEffect(state.finished) {
		if (state.finished) {
			onNavigateUp()
		}
	}
}

@Composable
fun NoteDetailContent(
	isLoading: Boolean,
	title: String,
	note: String,
	error: String?,
	isPinned: Boolean,
	showSaveButton: Boolean,
	onTitleChange: (String) -> Unit,
	onNoteChange: (String) -> Unit,
	onPinClick: () -> Unit,
	onSaveClick: () -> Unit,
	onNavigateUp: () -> Unit,
	onDeleteClick: () -> Unit,
) {
	if (isLoading) {
		LoaderDialog()
	}
	
	val focusRequester = remember { FocusRequester() }
	
	NoteItScaffold(
		error = error,
		modifier = Modifier
			.focusRequester(focusRequester)
			.focusable(true),
		appBar = {
			NoteItAppBar(
				onNavigateUp = onNavigateUp,
				actions = {
					NoteDetailActions(
						isPinned = isPinned,
						onPinClick = onPinClick,
						onDeleteClick = onDeleteClick,
					)
				}
			)
		},
		content = {
			NoteDetailBody(
				title = title,
				onTitleChange = onTitleChange,
				note = note,
				onNoteChange = onNoteChange
			)
		},
		floatingActionButton = {
			if (showSaveButton) {
				ExtendedFloatingActionButton(
					text = { Text("Save", color = Color.White) },
					icon = { Icon(Icons.Filled.Done, "Save", tint = Color.White) },
					onClick = onSaveClick,
					backgroundColor = MaterialTheme.colors.primary
				)
			}
		}
	)
}

@Composable
private fun NoteDetailActions(
	isPinned: Boolean,
	onPinClick: () -> Unit,
	onDeleteClick: () -> Unit,
) {
	var dropdownExpanded by remember { mutableStateOf(false) }
	PinAction(isPinned, onClick = onPinClick)
	DeleteAction(onClick = onDeleteClick)
}

@Composable
private fun NoteDetailBody(
	title: String,
	onTitleChange: (String) -> Unit,
	note: String,
	onNoteChange: (String) -> Unit
) {
	Column(
		Modifier
			.verticalScroll(rememberScrollState())
			.padding(16.dp)
	) {
		NoteTitleField(
			modifier = Modifier
				.fillMaxWidth()
				.background(MaterialTheme.colors.background),
			value = title,
			onTextChange = onTitleChange
		)
		
		NoteField(
			modifier = Modifier
				.fillMaxWidth()
				.wrapContentHeight()
				.padding(top = 32.dp)
				.background(MaterialTheme.colors.background),
			value = note,
			onTextChange = onNoteChange
		)
	}
	
}

@Composable
fun DeleteNoteConfirmation(show: Boolean, onConfirm: () -> Unit, onDismiss: () -> Unit) {
	if (show) {
		ConfirmationDialog(
			title = "Delete?",
			message = "Sure want to delete this note?",
			onConfirmedYes = onConfirm,
			onConfirmedNo = onDismiss,
			onDismissed = onDismiss
		)
	}
}