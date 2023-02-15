package dev.aashishtathod.noteit.domain.exceptions

import dev.aashishtathod.noteit.domain.model.LoginValidation

class AuthValidationException(private val validation: LoginValidation) : Exception()

