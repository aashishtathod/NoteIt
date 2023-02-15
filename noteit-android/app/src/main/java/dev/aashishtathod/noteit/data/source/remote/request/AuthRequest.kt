package dev.aashishtathod.noteit.data.source.remote.request

data class AuthRequest(
	val username: String,
	val password: String,
	val name: String? = null
	)
