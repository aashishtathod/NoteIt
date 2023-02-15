package dev.aashishtathod.noteit.domain.model

data class LoginValidation(
    var isValidUsername: Boolean = true,
    var isValidPassword: Boolean = true
)
