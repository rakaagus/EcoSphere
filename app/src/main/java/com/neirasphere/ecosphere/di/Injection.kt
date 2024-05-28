package com.neirasphere.ecosphere.di

import android.content.Context
import com.neirasphere.ecosphere.data.AppRepository
import com.neirasphere.ecosphere.data.RecycleRepository
import com.neirasphere.ecosphere.data.repository.MapRepositoryImpl
import com.neirasphere.ecosphere.data.EducationRepository
import com.neirasphere.ecosphere.data.preferences.AppDataStore
import com.neirasphere.ecosphere.data.preferences.appDataStore

object Injection {
    fun provideAppRepo(context: Context): AppRepository{
        val dsApp = AppDataStore.getInstance(context.appDataStore)
        return AppRepository(
            appDataStore = dsApp
        )
    }

    fun provideEducationRepository(): EducationRepository {
        return EducationRepository.getInstance()
    }
    
    fun provideRecycleRepository(): RecycleRepository {
        return RecycleRepository.getInstance()
    }
}