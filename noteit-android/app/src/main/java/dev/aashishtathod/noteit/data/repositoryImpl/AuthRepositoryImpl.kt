package dev.aashishtathod.noteit.data.repositoryImpl

import dev.aashishtathod.noteit.core.utils.Either
import dev.aashishtathod.noteit.data.source.remote.dataSource.UserRemoteDataSource
import dev.aashishtathod.noteit.data.source.remote.request.AuthRequest
import dev.aashishtathod.noteit.domain.model.AuthCredential
import dev.aashishtathod.noteit.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
	private val userRemoteDataSource: UserRemoteDataSource
) : AuthRepository {
	
	override suspend fun signup(
		name: String,
		username: String,
		password: String
	): Either<AuthCredential> {
		val result = userRemoteDataSource.signup(AuthRequest(username, password, name))
		
		return when (result) {
			is Either.Success -> Either.Success(AuthCredential(result.data.token))
			is Either.Error -> Either.Error(result.message)
		}
	}
	
	override suspend fun login(
		username: String,
		password: String
	): Either<AuthCredential> {
		val result = userRemoteDataSource.login(AuthRequest(username, password))
		
		return when (result) {
			is Either.Success -> Either.Success(AuthCredential(result.data.token))
			is Either.Error -> Either.Error(result.message)
		}
	}
}
