package dev.aashishtathod.noteit.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.aashishtathod.noteit.data.source.remote.api.AuthService
import dev.aashishtathod.noteit.data.source.remote.api.NoteService
import dev.aashishtathod.noteit.data.source.remote.dataSource.NoteRemoteDataSource
import dev.aashishtathod.noteit.data.source.remote.dataSource.UserRemoteDataSource

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {
	
	@Provides
	fun authRemoteDataSource(
		authService: AuthService,
	): UserRemoteDataSource = UserRemoteDataSource(authService)
	
	@Provides
	fun noteRemoteDataSource(
		noteService: NoteService,
	): NoteRemoteDataSource = NoteRemoteDataSource(noteService)
}
