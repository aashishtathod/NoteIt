package dev.aashishtathod.noteit.data.source.remote.dataSource

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.aashishtathod.noteit.core.data.BaseRemoteDataSource
import dev.aashishtathod.noteit.core.utils.Either
import dev.aashishtathod.noteit.data.source.remote.api.AuthService
import dev.aashishtathod.noteit.data.source.remote.dto.AuthResponse
import dev.aashishtathod.noteit.data.source.remote.request.AuthRequest
import javax.inject.Inject


class UserRemoteDataSource @Inject constructor(
    private val apiService: AuthService
) : BaseRemoteDataSource() {
    
    suspend fun signup(authRequest: AuthRequest): Either<AuthResponse> = safeApiCall {
        apiService.signup(authRequest)
    }
	
    suspend fun login(authRequest: AuthRequest): Either<AuthResponse> = safeApiCall {
        apiService.login(authRequest)
    }
}
