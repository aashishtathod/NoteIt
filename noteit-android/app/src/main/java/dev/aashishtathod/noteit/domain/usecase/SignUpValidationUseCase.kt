package dev.aashishtathod.noteit.domain.usecase

import dev.aashishtathod.noteit.domain.model.SignUpValidation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SignUpValidationUseCase @Inject constructor() {
	
	operator fun invoke(
		name: String,
		username: String,
		password: String,
		confirmPassword: String
	): Flow<SignUpValidation> =
		flow {
			val validation = SignUpValidation().apply {
				isValidName = isValidName(name)
				isValidUsername = isValidUsername(username)
				isValidPassword = isValidPassword(password)
				isValidConfirmPassword =
					arePasswordAndConfirmPasswordSame(password, confirmPassword)
			}
			emit(validation)
		}
	
	private fun isValidName(name: String): Boolean = name.trim().length in (2..24)
	
	private fun isValidUsername(username: String): Boolean = username.trim().length in (4..30)
	
	private fun isValidPassword(password: String): Boolean = password.trim().length in (8..50)
	
	private fun arePasswordAndConfirmPasswordSame(password: String, confirmPassword: String) =
		password == confirmPassword
}