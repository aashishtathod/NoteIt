package dev.aashishtathod.noteit.data.source.remote.api

import dev.aashishtathod.noteit.data.source.remote.dto.AuthResponse
import dev.aashishtathod.noteit.data.source.remote.request.AuthRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthService {
	
    @POST("/auth/register")
    suspend fun signup(@Body authRequest: AuthRequest): AuthResponse
	
    @POST("/auth/login")
    suspend fun login(@Body authRequest: AuthRequest): AuthResponse
}
