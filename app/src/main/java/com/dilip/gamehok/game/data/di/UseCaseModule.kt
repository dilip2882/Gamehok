package com.dilip.gamehok.game.data.di

import com.dilip.gamehok.game.domain.repository.GameRepository
import com.dilip.gamehok.game.domain.usecase.GameListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGameListUseCase(repository: GameRepository): GameListUseCase {
        return GameListUseCase(repository)
    }
}
