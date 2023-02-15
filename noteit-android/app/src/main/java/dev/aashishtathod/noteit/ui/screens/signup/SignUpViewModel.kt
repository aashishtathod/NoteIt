package dev.aashishtathod.noteit.ui.screens.signup

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.aashishtathod.noteit.core.presentation.BaseViewModel
import dev.aashishtathod.noteit.core.utils.Either
import dev.aashishtathod.noteit.domain.usecase.SaveTokenUseCase
import dev.aashishtathod.noteit.domain.usecase.SignUpUseCase
import dev.aashishtathod.noteit.domain.usecase.SignUpValidationUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
	private val signUpUseCase: SignUpUseCase,
	private val signUpValidationUseCase: SignUpValidationUseCase,
	private val saveTokenUseCase: SaveTokenUseCase
) : BaseViewModel<SignUpState>(SignUpState()) {
	
	fun setName(name: String) {
		setState { state -> state.copy(name = name) }
	}
	
	fun setUsername(username: String) {
		setState { state -> state.copy(username = username) }
	}
	
	fun setPassword(password: String) {
		setState { state -> state.copy(password = password) }
	}
	
	fun setConfirmPassword(password: String) {
		setState { state -> state.copy(confirmPassword = password) }
	}
	
	fun validateCredentials() {
		viewModelScope.launch {
			val name = currentState.name
			val username = currentState.username
			val password = currentState.password
			val confirmPassword = currentState.confirmPassword
			
			
			signUpValidationUseCase(name, username, password,confirmPassword).collect {
				setState { state ->
					state.copy(
						isValidName = it.isValidName,
						isValidUsername = it.isValidUsername,
						isValidPassword = it.isValidPassword,
						isValidConfirmPassword = it.isValidConfirmPassword
					)
				}
				if (it.isValidUsername && it.isValidPassword && it.isValidName && it.isValidConfirmPassword) {
					signup(name, username, password)
				}
			}
		}
	}
	
	
	private suspend fun signup(name: String, username: String, password: String) {
		setState { it.copy(isLoading = true) }
		signUpUseCase(name, username, password).collect {
			when (it) {
				is Either.Success -> {
					saveTokenUseCase.invoke(it.data.token)
					setState { state ->
						state.copy(isLoading = false, isLoggedIn = true, error = null)
					}
				}
				is Either.Error -> {
					setState { state ->
						state.copy(
							isLoading = false,
							isLoggedIn = false,
							error = it.message
						)
					}
				}
			}
		}
		
	}
	
	fun clearError() = setState { state -> state.copy(error = null) }
	
	
}