package dev.aashishtathod.noteit.ui.screens.addNote

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.aashishtathod.noteit.core.utils.ext.collectState
import dev.aashishtathod.noteit.ui.components.scaffold.NoteItAppBar
import dev.aashishtathod.noteit.ui.components.scaffold.NoteItScaffold
import dev.shreyaspatil.noty.composeapp.component.text.NoteField
import dev.shreyaspatil.noty.composeapp.component.text.NoteTitleField

@Composable
fun AddNoteScreen(
	viewModel: AddNoteViewModel,
	onNavigateUp: () -> Unit
) {
	val state by viewModel.collectState()
	
	AddNotesContent(
		isLoading = state.isAdding,
		title = state.title,
		note = state.note,
		showSaveFab = state.showSave,
		onTitleChange = viewModel::setTitle,
		onNoteChange = viewModel::setNote,
		onClickAddNote = viewModel::add,
		error = state.errorMessage,
		onNavigateUp = onNavigateUp
	)
	
	LaunchedEffect(state.added) {
		if (state.added) {
			onNavigateUp()
		}
	}
}

@Composable
fun AddNotesContent(
	isLoading: Boolean,
	title: String,
	note: String,
	showSaveFab: Boolean,
	onTitleChange: (String) -> Unit,
	onNoteChange: (String) -> Unit,
	onClickAddNote: () -> Unit,
	error: String?,
	onNavigateUp: () -> Unit
) {
	NoteItScaffold(
		isLoading = isLoading,
		error = error,
		appBar = { NoteItAppBar(title = "Add Note", onNavigateUp = onNavigateUp) },
		content = {
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
					onTextChange = onTitleChange,
				)
				
				NoteField(
					modifier = Modifier
						.fillMaxWidth()
						.fillMaxHeight()
						.padding(top = 32.dp)
						.background(MaterialTheme.colors.background),
					value = note,
					onTextChange = onNoteChange
				)
			}
		},
		floatingActionButton = {
			if (showSaveFab) {
				ExtendedFloatingActionButton(
					text = { Text("Save", color = Color.White) },
					icon = {
						Icon(
							Icons.Filled.Done,
							"Save",
							tint = Color.White
						)
					},
					onClick = onClickAddNote,
					backgroundColor = MaterialTheme.colors.primary
				)
			}
		}
	)
}