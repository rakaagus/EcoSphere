package com.neirasphere.ecosphere.di

import com.neirasphere.ecosphere.data.repository.LearnRepositoryImpl
import com.neirasphere.ecosphere.data.repository.MapRepositoryImpl
import com.neirasphere.ecosphere.domain.repository.LearnRepository
import com.neirasphere.ecosphere.domain.repository.MapRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideLearnRepo(learnRepositoryImpl: LearnRepositoryImpl): LearnRepository

    @Binds
    @Singleton
    abstract fun provideMapRepo(mapRepositoryImpl: MapRepositoryImpl): MapRepository
}