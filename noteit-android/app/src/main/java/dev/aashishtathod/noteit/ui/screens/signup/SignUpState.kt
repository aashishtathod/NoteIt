package dev.aashishtathod.noteit.ui.screens.signup

import dev.aashishtathod.noteit.core.presentation.State

data class SignUpState(
	val isLoading: Boolean = false,
	val isLoggedIn: Boolean = false,
	val name: String = "",
	val username: String = "",
	val password: String = "",
	val confirmPassword: String = "",
	val isValidName: Boolean? = null,
	val isValidUsername: Boolean? = null,
	val isValidPassword: Boolean? = null,
	val isValidConfirmPassword: Boolean? = null,
	val error: String? = null
) : State