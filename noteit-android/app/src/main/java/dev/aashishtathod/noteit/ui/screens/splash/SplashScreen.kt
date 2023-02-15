package dev.aashishtathod.noteit.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.aashishtathod.noteit.R
import dev.aashishtathod.noteit.core.utils.NotyPreview
import dev.aashishtathod.noteit.core.utils.ext.collectState

@Composable
fun SplashScreen(
    viewModel: SplashViewModel,
    onNavigateToLogin: () -> Unit,
    onNavigateToNotes: () -> Unit
) {
    val state by viewModel.collectState()
	
    SplashContent(
        isLoggedIn = state.isLoggedIn,
        onNavigateToLogin,
        onNavigateToNotes
    )
}

@Composable
fun SplashContent(
    isLoggedIn: Boolean?,
    onNavigateToLogin: () -> Unit,
    onNavigateToNotes: () -> Unit
) {
    if (isLoggedIn == true) {
        onNavigateToNotes()
    }
	
    if (isLoggedIn == false) {
        onNavigateToLogin()
    }
	
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_noty_logo),
            contentDescription = "App Logo",
            modifier = Modifier
                .padding(top = 60.dp)
                .requiredSize(92.dp),
            contentScale = ContentScale.FillBounds
        )
		
        Spacer(modifier = Modifier.height(28.dp))
		
        Text(
            text = "NoteIt",
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            style = typography.h4,
            color = MaterialTheme.colors.background
        )
    }
}

@Preview
@Composable
fun PreviewLoginContent() = NotyPreview {
    SplashContent(
        isLoggedIn = false,
        onNavigateToLogin = { },
        onNavigateToNotes = {}
    )
}
