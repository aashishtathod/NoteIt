package dev.aashishtathod.noteit.domain.repository

import dev.aashishtathod.noteit.core.utils.Either
import dev.aashishtathod.noteit.domain.model.AuthCredential

interface AuthRepository {
	
	suspend fun signup(
		name: String,
		username: String,
		password: String
	): Either<AuthCredential>
	
	suspend fun login(
		username: String,
		password: String
	): Either<AuthCredential>
}
