package dev.aashishtathod.noteit.domain.usecase

import dev.aashishtathod.noteit.core.utils.Either
import dev.aashishtathod.noteit.domain.model.AuthCredential
import dev.aashishtathod.noteit.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
	
    operator fun invoke(
        username: String,
        password: String
    ): Flow<Either<AuthCredential>> = flow {
        val result = authRepository.login(username, password)
        emit(result)
    }.flowOn(Dispatchers.IO)
}
