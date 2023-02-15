import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import dev.aashishtathod.noteit.R
import dev.shreyaspatil.noty.composeapp.component.text.NoteItTextField

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    label: String = "Password",
    value: String = "",
    isError: Boolean = false,
    onValueChange: (String) -> Unit
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
	
    NoteItTextField(
        value = value,
        label = label,
        onValueChange = onValueChange,
        modifier = modifier,
        leadingIcon = { Icon(Icons.Outlined.Home, label) },
        visualTransformation = if (isPasswordVisible) VisualTransformation.None
        else PasswordVisualTransformation(),
        isError = isError,
        helperText = stringResource(R.string.message_field_password_invalid),
        trailingIcon = {
            val image = if (isPasswordVisible) Icons.Filled.Star
            else Icons.Filled.Home
            val description = if (isPasswordVisible) "Hide password" else "Show password"
			
            IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                Icon(
                    imageVector = image,
                    contentDescription = description,
                    modifier = Modifier.testTag("TogglePasswordVisibility")
                )
            }
        }
    )
}
