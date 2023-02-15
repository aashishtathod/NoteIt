package dev.aashishtathod.noteit.data.source.remote.dto

import dev.aashishtathod.noteit.core.data.BaseResponse

data class AuthResponse(
    override val status: Int,
    override val message: String,
    val token: String
) : BaseResponse
