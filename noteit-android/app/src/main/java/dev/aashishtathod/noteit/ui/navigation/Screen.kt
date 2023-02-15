package dev.aashishtathod.noteit.ui.navigation

sealed class Screen(val route: String, val name: String) {
	
    object Splash : Screen("splash", "Splash")
	
    object SignUp : Screen("signup", "Sign Up")
	
    object Login : Screen("login", "Login")
	
    object Notes : Screen("notes", "Notes")
	
    object NotesDetail : Screen("note/{noteId}", "Note details") {
		
        fun route(noteId: String) = "note/$noteId"
		
        const val ARG_NOTE_ID: String = "noteId"
    }
	
    object AddNote : Screen("note/new", "New note")
	
    object About : Screen("about", "About")
}
