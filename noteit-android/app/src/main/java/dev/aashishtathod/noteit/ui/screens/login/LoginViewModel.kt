package dev.aashishtathod.noteit.ui.screens.login

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.aashishtathod.noteit.core.presentation.BaseViewModel
import dev.aashishtathod.noteit.core.utils.Either
import dev.aashishtathod.noteit.domain.usecase.AuthValidationUseCase
import dev.aashishtathod.noteit.domain.usecase.LoginUseCase
import dev.aashishtathod.noteit.domain.usecase.SaveTokenUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
	private val loginUseCase: LoginUseCase,
	private val authValidationUseCase: AuthValidationUseCase,
	private val saveTokenUseCase: SaveTokenUseCase
) : BaseViewModel<LoginState>(initialState = LoginState()) {
	
	fun setUsername(username: String) {
		setState { state -> state.copy(username = username) }
	}
	
	fun setPassword(password: String) {
		setState { state -> state.copy(password = password) }
	}
	
	fun validateCredentials() {
		viewModelScope.launch {
			val username = currentState.username
			val password = currentState.password
			
			authValidationUseCase(username, password).collect {
				setState { state ->
					state.copy(
						isValidUsername = it.isValidUsername,
						isValidPassword = it.isValidPassword
					)
				}
				if (it.isValidUsername and it.isValidPassword) {
					login(username, password)
				}
			}
		}
	}
	
	private suspend fun login(username: String, password: String) {
		setState { it.copy(isLoading = true) }
		loginUseCase(username, password).collect {
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
	
}
