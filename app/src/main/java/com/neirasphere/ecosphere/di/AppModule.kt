package com.neirasphere.ecosphere.di

import com.neirasphere.ecosphere.domain.usecase.appusecase.AppUseCase
import com.neirasphere.ecosphere.domain.usecase.appusecase.AppUseCaseInteract
import com.neirasphere.ecosphere.domain.usecase.classify.ClassifyUseCase
import com.neirasphere.ecosphere.domain.usecase.classify.ClassifyUseCaseInteract
import com.neirasphere.ecosphere.domain.usecase.education.EducationUseCase
import com.neirasphere.ecosphere.domain.usecase.education.EducationUseCaseInteract
import com.neirasphere.ecosphere.domain.usecase.learn.LearnUseCase
import com.neirasphere.ecosphere.domain.usecase.learn.LearnUseCaseInteract
import com.neirasphere.ecosphere.domain.usecase.map.MapUseCase
import com.neirasphere.ecosphere.domain.usecase.map.MapUseCaseInteract
import com.neirasphere.ecosphere.domain.usecase.recycle.RecycleUseCase
import com.neirasphere.ecosphere.domain.usecase.recycle.RecycleUseCaseInteract
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

    @Binds
    @Singleton
    abstract fun bindEducationRepo(educationUseCaseInteract: EducationUseCaseInteract): EducationUseCase

    @Binds
    @Singleton
    abstract fun bindRecycleRepo(recycleUseCaseInteract: RecycleUseCaseInteract): RecycleUseCase

    @Binds
    @Singleton
    abstract fun bindAppRepo(appUseCaseInteract: AppUseCaseInteract) : AppUseCase

    @Binds
    @Singleton
    abstract fun bindClassifyRepo(classifyUseCaseInteract: ClassifyUseCaseInteract) : ClassifyUseCase
}