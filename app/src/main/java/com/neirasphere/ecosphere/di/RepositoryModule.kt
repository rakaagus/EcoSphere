package com.neirasphere.ecosphere.di

import com.neirasphere.ecosphere.data.repository.AppRepositoryImpl
import com.neirasphere.ecosphere.data.repository.ClassificationRepositoryImpl
import com.neirasphere.ecosphere.data.repository.ClassifyHistoryRepositoryImpl
import com.neirasphere.ecosphere.data.repository.CommunityRepositoryImpl
import com.neirasphere.ecosphere.data.repository.EduHistoryRepositoryImpl
import com.neirasphere.ecosphere.data.repository.EducationRepositoryImpl
import com.neirasphere.ecosphere.data.repository.LearnRepositoryImpl
import com.neirasphere.ecosphere.data.repository.MapRepositoryImpl
import com.neirasphere.ecosphere.data.repository.RecycleRepositoryImpl
import com.neirasphere.ecosphere.domain.repository.AppRepository
import com.neirasphere.ecosphere.domain.repository.ClassificationRepository
import com.neirasphere.ecosphere.domain.repository.ClassifyHistoryRepository
import com.neirasphere.ecosphere.domain.repository.CommunityRepository
import com.neirasphere.ecosphere.domain.repository.EduHistoryRepository
import com.neirasphere.ecosphere.domain.repository.EducationRepository
import com.neirasphere.ecosphere.domain.repository.LearnRepository
import com.neirasphere.ecosphere.domain.repository.MapRepository
import com.neirasphere.ecosphere.domain.repository.RecycleRepository
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

    @Binds
    @Singleton
    abstract fun provideEducationRepo(educationRepositoryImpl: EducationRepositoryImpl): EducationRepository

    @Binds
    @Singleton
    abstract fun provideRecycleRepo(recycleRepositoryImpl: RecycleRepositoryImpl): RecycleRepository

    @Binds
    @Singleton
    abstract fun provideAppRepo(appRepositoryImpl: AppRepositoryImpl): AppRepository

    @Binds
    @Singleton
    abstract fun provideEduHistoryRepo(eduHistoryRepositoryImpl: EduHistoryRepositoryImpl) : EduHistoryRepository

    @Binds
    @Singleton
    abstract fun provideClassifyRepo(classificationRepositoryImpl: ClassificationRepositoryImpl) : ClassificationRepository

    @Binds
    @Singleton
    abstract fun provideClassifyHistoryRepo(classifyHistoryRepositoryImpl: ClassifyHistoryRepositoryImpl) : ClassifyHistoryRepository
  
    @Binds
    @Singleton
    abstract fun provideCommunityRepo(communityRepositoryImpl: CommunityRepositoryImpl) : CommunityRepository
}