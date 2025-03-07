package com.dilip.gamehok.game.data.di

import com.dilip.gamehok.game.data.repository.GameRepositoryImpl
import com.dilip.gamehok.game.data.repository.TournamentRepositoryImpl
import com.dilip.gamehok.game.domain.repository.GameRepository
import com.dilip.gamehok.game.domain.repository.TournamentRepository
import com.dilip.gamehok.game.domain.usecase.GameListUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindGameRepository(gameRepositoryImpl: GameRepositoryImpl): GameRepository

    @Binds
    abstract fun bindTournamentRepository(tournamentRepositoryImpl: TournamentRepositoryImpl): TournamentRepository
}
