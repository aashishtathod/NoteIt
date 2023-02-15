package dev.aashishtathod.noteit.ui.components.note

import NoteCard
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import dev.aashishtathod.noteit.domain.model.Note

@Composable
fun NotesList(notes: List<Note>, onClick: (note:Note) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 4.dp),
        modifier = Modifier.testTag("notesList")
    ) {
        items(
            items = notes,
            itemContent = { note ->
                NoteCard(
                    title = note.title,
                    note = note.note,
                    isPinned = note.isPinned,
                    onNoteClick = { onClick(note) }
                )
            },
            key = {
                Triple(it.noteId, it.title, it.note)
            }
        )
    }
}
