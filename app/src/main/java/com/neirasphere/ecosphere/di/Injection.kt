package com.neirasphere.ecosphere.di

import android.content.Context
import com.neirasphere.ecosphere.data.AppRepository
import com.neirasphere.ecosphere.data.LearnRepository
import com.neirasphere.ecosphere.data.RecycleRepository
import com.neirasphere.ecosphere.data.MapRepository
import com.neirasphere.ecosphere.data.EducationRepository
import com.neirasphere.ecosphere.data.preferences.AppDataStore
import com.neirasphere.ecosphere.data.preferences.appDataStore

object Injection {
    fun provideRepository(): LearnRepository{
        return LearnRepository.getInstance()
    }

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
    
    fun provideMapRepository(): MapRepository {
        return MapRepository.getInstance()
    }
}