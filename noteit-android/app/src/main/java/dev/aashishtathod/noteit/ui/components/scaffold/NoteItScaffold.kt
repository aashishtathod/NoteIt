package dev.aashishtathod.noteit.ui.components.scaffold

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.aashishtathod.noteit.core.utils.NotyPreview
import dev.aashishtathod.noteit.ui.components.dialog.FailureDialog
import dev.aashishtathod.noteit.ui.components.dialog.LoaderDialog

@Composable
fun NoteItScaffold(
    modifier: Modifier = Modifier,
    appBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    isLoading: Boolean = false,
    error: String? = null
) {
    if (isLoading) {
        LoaderDialog()
    }
    if (error != null) {
        FailureDialog(error)
    }
	
    Scaffold(
        modifier = modifier,
        topBar = appBar,
        content = content,
        floatingActionButton = floatingActionButton
    )
}


