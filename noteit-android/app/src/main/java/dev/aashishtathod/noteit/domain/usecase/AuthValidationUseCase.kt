package dev.aashishtathod.noteit.domain.usecase

import dev.aashishtathod.noteit.domain.exceptions.AuthValidationException
import dev.aashishtathod.noteit.domain.model.LoginValidation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthValidationUseCase @Inject constructor() {
	
    @Throws(AuthValidationException::class)
    operator fun invoke(
        username: String,
        password: String
    ): Flow<LoginValidation> = flow {
        val validation = LoginValidation().apply {
            isValidUsername = isValidUsername(username)
            isValidPassword = isValidPassword(password)
        }
        emit(validation)
    }
	
    private fun isValidUsername(username: String): Boolean = username.trim().length in (4..30)
    private fun isValidPassword(password: String): Boolean = password.trim().length in (8..50)
    
}
