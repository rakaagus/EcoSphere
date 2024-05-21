package com.neirasphere.ecosphere.di

import com.neirasphere.ecosphere.data.LearnRepository
import com.neirasphere.ecosphere.data.local.EducationRepository

object Injection {
    fun provideRepository(): LearnRepository{
        return LearnRepository.getInstance()
    }
    fun provideEducationRepository(): EducationRepository {
        return EducationRepository.getInstance()
    }
}