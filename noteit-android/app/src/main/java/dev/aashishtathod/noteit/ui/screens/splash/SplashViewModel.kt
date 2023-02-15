package dev.aashishtathod.noteit.ui.screens.splash

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.aashishtathod.noteit.core.presentation.BaseViewModel
import dev.aashishtathod.noteit.domain.usecase.IsUserLoggedInUseCase
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val isUserLoggedInUseCase: IsUserLoggedInUseCase
) : BaseViewModel<SplashState>(initialState = SplashState()) {
	
    init {
        isUserLoggedIn()
    }
	
    fun isUserLoggedIn() {
        setState { state ->
            state.copy(isLoggedIn = isUserLoggedInUseCase())
        }
    }
}
