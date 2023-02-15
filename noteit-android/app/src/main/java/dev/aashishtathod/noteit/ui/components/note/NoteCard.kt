import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.aashishtathod.noteit.core.utils.NotyPreview
import dev.aashishtathod.noteit.R

@Composable
fun NoteCard(title: String, note: String, isPinned: Boolean, onNoteClick: () -> Unit) {
	Card(
		shape = RoundedCornerShape(4.dp),
		backgroundColor = MaterialTheme.colors.surface,
		modifier = Modifier
			.padding(horizontal = 8.dp, vertical = 4.dp)
			.fillMaxWidth()
			.wrapContentHeight()
			.clickable { onNoteClick() },
		elevation = 0.dp
	) {
		Column(
			modifier = Modifier
				.padding(16.dp)
		) {
			Row(Modifier.fillMaxWidth()) {
				Text(
					text = title,
					style = MaterialTheme.typography.h6,
					color = MaterialTheme.colors.onPrimary,
					fontWeight = FontWeight.Bold,
					modifier = Modifier.weight(1f)
				)
				if (isPinned) {
					Icon(
						painterResource(id = R.drawable.ic_pinned),
						contentDescription = "Pinned Note",
						modifier = Modifier.padding(8.dp)
					)
				}
			}
			Spacer(modifier = Modifier.height(10.dp))
			Text(
				text = note,
				style = MaterialTheme.typography.body1,
				color = MaterialTheme.colors.onPrimary,
				lineHeight = 24.sp,
				maxLines = 2,
				overflow = TextOverflow.Ellipsis
			)
		}
	}
}

@Preview
@Composable
fun PreviewNoteCard() = NotyPreview {
	NoteCard(
		title = "Lorem Ipsum",
		note = "Here is note body...",
		isPinned = true,
		onNoteClick = {}
	)
}