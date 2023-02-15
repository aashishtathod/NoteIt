package dev.aashishtathod.noteit.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dev.aashishtathod.noteit.ui.screens.SignUpScreen
import dev.aashishtathod.noteit.ui.screens.addNote.AddNoteScreen
import dev.aashishtathod.noteit.ui.screens.login.LoginScreen
import dev.aashishtathod.noteit.ui.screens.noteDetail.NoteDetailsScreen
import dev.aashishtathod.noteit.ui.screens.notes.NotesScreen
import dev.aashishtathod.noteit.ui.screens.splash.SplashScreen

const val NOTE_IT_NAV_HOST_ROUTE = "noty-main-route"

@Composable
fun NoteItNavigation() {
	val navController = rememberNavController()
	
	NavHost(navController, startDestination = Screen.Splash.route, route = NOTE_IT_NAV_HOST_ROUTE) {
		
		composable(Screen.Splash.route) {
			SplashScreen(
				viewModel = hiltViewModel(),
				onNavigateToLogin = { navController.navigateToLogin() },
				onNavigateToNotes = { navController.popAllAndNavigateToNotes() }
			)
		}
		
		composable(Screen.Login.route) {
			LoginScreen(
				viewModel = hiltViewModel(),
				onNavigateToSignup = { navController.navigateToSignup() },
				onNavigateToNotes = { navController.popAllAndNavigateToNotes() }
			)
		}
		
		composable(Screen.SignUp.route) {
			SignUpScreen(
				viewModel = hiltViewModel(),
				onNavigateUp = { navController.navigateUp() },
				onNavigateToNotes = { navController.popAllAndNavigateToNotes() }
			)
		}
		
		composable(Screen.Notes.route) {
			NotesScreen(
				viewModel = hiltViewModel(),
				onNavigateToAddNote = { navController.navigateToAddNote() },
				onNavigateToNoteDetail = { navController.navigateToNoteDetail(it) },
				onNavigateToLogin = { /*navController.popAllAndNavigateToLogin()*/ }
			)
		}
		
		composable(Screen.AddNote.route) {
			AddNoteScreen(
				viewModel = hiltViewModel(),
				onNavigateUp = { navController.navigateUp() }
			)
		}
		
		composable(
			Screen.NotesDetail.route,
			arguments = listOf(
				navArgument(Screen.NotesDetail.ARG_NOTE_ID) { type = NavType.StringType }
			)
		) {
			val noteId = requireNotNull(it.arguments?.getString(Screen.NotesDetail.ARG_NOTE_ID))
			NoteDetailsScreen(
				noteId = noteId,
				viewModel = hiltViewModel(),
				onNavigateUp = { navController.navigateUp() }
			)
		}
		
	}
}

fun NavController.navigateToLogin() = navigate(Screen.Login.route)

fun NavController.navigateToSignup() = navigate(Screen.SignUp.route)

fun NavController.navigateToAbout() = navigate(Screen.About.route)

fun NavController.navigateToAddNote() = navigate(Screen.AddNote.route)

/**
 * Launches note detail screen for specified [noteId]
 */
fun NavController.navigateToNoteDetail(noteId: String) = navigate(Screen.NotesDetail.route(noteId))

/**
 * Clears backstack including current screen and navigates to Login Screen
 */
/*fun NavController.popAllAndNavigateToLogin() = navigate(Screen.Login.route) {
	popUpTo(NOTY_NAV_HOST_ROUTE)
	launchSingleTop = true
}*/


fun NavController.popAllAndNavigateToNotes() = navigate(Screen.Notes.route) {
	launchSingleTop = true
	popUpTo(NOTE_IT_NAV_HOST_ROUTE)
}
