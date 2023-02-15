package dev.aashishtathod.noteit.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.aashishtathod.noteit.data.source.remote.api.AuthService
import dev.aashishtathod.noteit.data.source.remote.api.NoteService
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkServiceModule {
	@Provides
	fun provideAuthServices(retrofit: Retrofit): AuthService =
		retrofit.create(AuthService::class.java)
	
	@Provides
	fun provideNoteServices(retrofit: Retrofit): NoteService =
		retrofit.create(NoteService::class.java)
}
