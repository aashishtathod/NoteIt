package dev.aashishtathod.noteit.domain.model

data class SignUpValidation(
	var isValidName: Boolean = true,
	var isValidUsername: Boolean = true,
	var isValidPassword: Boolean = true,
	var isValidConfirmPassword: Boolean = true
)
