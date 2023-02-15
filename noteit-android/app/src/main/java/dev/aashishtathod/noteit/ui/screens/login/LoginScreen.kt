package dev.aashishtathod.noteit.ui.screens.login

import PasswordTextField
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.aashishtathod.noteit.R
import dev.aashishtathod.noteit.core.utils.NotyPreview
import dev.aashishtathod.noteit.core.utils.ext.collectState
import dev.aashishtathod.noteit.ui.components.button.NoteItFullWidthButton
import dev.aashishtathod.noteit.ui.components.dialog.FailureDialog
import dev.aashishtathod.noteit.ui.components.dialog.LoaderDialog
import dev.shreyaspatil.noty.composeapp.component.text.UsernameTextField

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onNavigateToSignup: () -> Unit,
    onNavigateToNotes: () -> Unit
) {
    val state by viewModel.collectState()
	
    LoginContent(
        isLoading = state.isLoading,
        username = state.username,
        password = state.password,
        isValidUsername = state.isValidUsername ?: true,
        isValidPassword = state.isValidPassword ?: true,
        onUsernameChange = viewModel::setUsername,
        onPasswordChange = viewModel::setPassword,
        onLoginClick = viewModel::validateCredentials,
        onSignupClick = onNavigateToSignup,
        error = state.error
    )
	
    LaunchedEffect(state.isLoggedIn) {
        if (state.isLoggedIn) {
            onNavigateToNotes()
        }
    }
}

@Composable
fun LoginContent(
    isLoading: Boolean,
    username: String,
    isValidUsername: Boolean,
    password: String,
    isValidPassword: Boolean,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onSignupClick: () -> Unit,
    error: String?
) {
    if (isLoading) {
        LoaderDialog()
    }
	
    if (error != null) {
        FailureDialog(error)
    }
	
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.surface)
            .verticalScroll(rememberScrollState())
    ) {
        TopGreeting()
		
        LoginForm(
            username = username,
            isValidUsername = isValidUsername,
            onUsernameChange = onUsernameChange,
            password = password,
            isValidPassword = isValidPassword,
            onPasswordChange = onPasswordChange,
            onLoginClick = onLoginClick
        )
		
        SignUpLink(Modifier.align(Alignment.CenterHorizontally), onSignupClick = onSignupClick)
    }
}

@Composable
private fun TopGreeting() {
    Column(Modifier.fillMaxWidth()) {
        Image(
            contentDescription = "App Logo",
            painter = painterResource(id = R.drawable.noteit_app_logo),
            modifier = Modifier
                .padding(top = 60.dp)
                .requiredSize(92.dp)
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.FillBounds
        )
		
        Text(
            text = "Welcome\nback",
            style = typography.h4,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 30.dp)
        )
    }
}

@Composable
private fun LoginForm(
    username: String,
    isValidUsername: Boolean,
    password: String,
    isValidPassword: Boolean,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit
) {
    UsernameTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(MaterialTheme.colors.background),
        value = username,
        onValueChange = onUsernameChange,
        isError = !isValidUsername
    )
	
    PasswordTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(MaterialTheme.colors.background),
        value = password,
        onValueChange = onPasswordChange,
        isError = !isValidPassword
    )
	
    NoteItFullWidthButton(
        text = "Login",
        onClick = onLoginClick,
        modifier = Modifier.padding(vertical = 16.dp, horizontal = 16.dp)
    )
}

@Composable
private fun SignUpLink(modifier: Modifier, onSignupClick: () -> Unit) {
    Text(
        text = buildAnnotatedString {
            append("Don't have an account? Signup")
            addStyle(SpanStyle(color = MaterialTheme.colors.primary), 23, this.length)
            toAnnotatedString()
        },
        style = typography.subtitle1,
        modifier = modifier
            .padding(vertical = 24.dp, horizontal = 16.dp)
            .clickable(onClick = onSignupClick)
    )
}

@Preview
@Composable
fun PreviewLoginContent() = NotyPreview {
    LoginContent(
        isLoading = false,
        username = "johndoe",
        onUsernameChange = {},
        password = "password",
        onPasswordChange = {},
        onLoginClick = {},
        onSignupClick = {},
        isValidPassword = false,
        isValidUsername = false,
        error = null
    )
}
