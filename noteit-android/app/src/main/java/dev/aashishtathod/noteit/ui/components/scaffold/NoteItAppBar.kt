package dev.aashishtathod.noteit.ui.components.scaffold

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.aashishtathod.noteit.R
import dev.aashishtathod.noteit.core.utils.NotyPreview

@Composable
fun NoteItAppBar(
    title: String = "NoteIt",
    onNavigateUp: (() -> Unit)? = null,
    actions: @Composable (RowScope.() -> Unit) = {}
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier.fillMaxWidth()
            )
        },
        navigationIcon = onNavigateUp?.let {
            val navigationIcon: @Composable () -> Unit = {
                IconButton(
                    modifier = Modifier.padding(start = 4.dp),
                    onClick = onNavigateUp
                ) {
                    Icon(
                        painterResource(R.drawable.ic_back),
                        "Back",
                        tint = MaterialTheme.colors.onPrimary
                    )
                }
            }
            navigationIcon
        },
        actions = actions,
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.onPrimary,
        elevation = 0.dp
    )
}

@Preview
@Composable
fun Preview() = NotyPreview {
    NoteItAppBar(
        onNavigateUp = {}
    )
}
