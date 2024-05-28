package com.neirasphere.ecosphere.di

import com.neirasphere.ecosphere.domain.usecase.learn.LearnUseCase
import com.neirasphere.ecosphere.domain.usecase.learn.LearnUseCaseInteract
import com.neirasphere.ecosphere.domain.usecase.map.MapUseCase
import com.neirasphere.ecosphere.domain.usecase.map.MapUseCaseInteract
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

    @Binds
    @Singleton
    abstract fun bindMapRepo(mapUseCaseInteract: MapUseCaseInteract): MapUseCase
}