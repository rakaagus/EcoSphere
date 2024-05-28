package com.neirasphere.ecosphere.di

import com.neirasphere.ecosphere.domain.repository.LearnRepository
import com.neirasphere.ecosphere.domain.usecase.LearnUseCase
import com.neirasphere.ecosphere.domain.usecase.LearnUseCaseInteract
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindLearnRepo(learnUseCaseInteract: LearnUseCaseInteract): LearnUseCase
}