package dev.aashishtathod.noteit.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.aashishtathod.noteit.data.repositoryImpl.AuthRepositoryImpl
import dev.aashishtathod.noteit.data.repositoryImpl.NoteRepositoryImpl
import dev.aashishtathod.noteit.data.source.remote.dataSource.NoteRemoteDataSource
import dev.aashishtathod.noteit.data.source.remote.dataSource.UserRemoteDataSource
import dev.aashishtathod.noteit.domain.repository.AuthRepository
import dev.aashishtathod.noteit.domain.repository.NoteRepository

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
	
    @Provides
    fun provideLoginRepository(
        remoteDataSource: UserRemoteDataSource
    ): AuthRepository = AuthRepositoryImpl(remoteDataSource)
    
    @Provides
    fun provideNoteRepository(
        remoteDataSource: NoteRemoteDataSource
    ): NoteRepository = NoteRepositoryImpl(remoteDataSource)
}
