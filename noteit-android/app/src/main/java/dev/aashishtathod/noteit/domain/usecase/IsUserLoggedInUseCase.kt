package dev.aashishtathod.noteit.domain.usecase

import dev.aashishtathod.noteit.core.data.AppPreferences
import javax.inject.Inject

class IsUserLoggedInUseCase @Inject constructor(
    private val appPreferences: AppPreferences
) {
	
    operator fun invoke(): Boolean {
        return appPreferences.isLoggedIn
    }
}
