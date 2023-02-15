package dev.aashishtathod.noteit.ui.components.actions

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.aashishtathod.noteit.R


@Composable
fun PinAction(isPinned: Boolean, onClick: () -> Unit) {
	val icon = painterResource(id = if (isPinned) R.drawable.ic_pinned else R.drawable.ic_unpinned)
	IconButton(onClick = onClick) {
		Icon(
			painter = icon,
			contentDescription = "Pinned Note",
			modifier = Modifier
				.padding(8.dp)
		)
	}
}

@Composable
fun DeleteAction(onClick: () -> Unit) {
	val icon = painterResource(R.drawable.ic_delete)
	IconButton(onClick = onClick) {
		Icon(
			painter = icon,
			contentDescription = "Delete",
			modifier = Modifier
				.padding(8.dp)
		)
	}
}
