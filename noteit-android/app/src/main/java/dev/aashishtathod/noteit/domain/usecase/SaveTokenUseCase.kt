package dev.aashishtathod.noteit.domain.usecase

import dev.aashishtathod.noteit.core.data.AppPreferences
import javax.inject.Inject

class SaveTokenUseCase @Inject constructor(
	private val appPreferences: AppPreferences
){
	operator fun invoke(token: String){
		appPreferences.userLoginToken = token
	}
}