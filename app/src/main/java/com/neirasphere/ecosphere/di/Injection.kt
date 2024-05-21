package com.neirasphere.ecosphere.di

import com.neirasphere.ecosphere.data.LearnRepository
import com.neirasphere.ecosphere.data.RecycleRepository
import com.neirasphere.ecosphere.data.MapRepository
import com.neirasphere.ecosphere.data.local.EducationRepository

object Injection {
    fun provideRepository(): LearnRepository{
        return LearnRepository.getInstance()
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