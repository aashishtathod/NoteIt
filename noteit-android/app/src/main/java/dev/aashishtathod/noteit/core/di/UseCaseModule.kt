package dev.aashishtathod.noteit.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.aashishtathod.noteit.core.data.AppPreferences
import dev.aashishtathod.noteit.domain.repository.AuthRepository
import dev.aashishtathod.noteit.domain.repository.NoteRepository
import dev.aashishtathod.noteit.domain.usecase.*

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
	
    @Provides
    fun logInUseCase(
	    authRepository: AuthRepository
    ): LoginUseCase = LoginUseCase(authRepository)
	
    @Provides
    fun authValidationUseCase(): AuthValidationUseCase = AuthValidationUseCase()
    
    @Provides
    fun saveTokenUseCase(
        appPreferences: AppPreferences
    ): SaveTokenUseCase = SaveTokenUseCase(appPreferences)
    
    @Provides
    fun noteValidationUseCase(): NoteValidationUseCase = NoteValidationUseCase()
    
    @Provides
    fun addNoteUseCase(
        noteRepository: NoteRepository
    ): AddNoteUseCase = AddNoteUseCase(noteRepository)
}
