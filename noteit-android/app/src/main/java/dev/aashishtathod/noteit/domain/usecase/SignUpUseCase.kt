package dev.aashishtathod.noteit.domain.usecase

import dev.aashishtathod.noteit.core.utils.Either
import dev.aashishtathod.noteit.domain.model.AuthCredential
import dev.aashishtathod.noteit.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import javax.inject.Inject

class SignUpUseCase @Inject constructor(
	private val authRepository: AuthRepository
) {
	operator fun invoke(
		name: String,
		username: String,
		password: String
	): Flow<Either<AuthCredential>> = flow {
		
		val response = authRepository.signup(name, username, password)
		emit(response)
	}
}