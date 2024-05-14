package com.neirasphere.ecosphere.di

import com.neirasphere.ecosphere.data.LearnRepository

object Injection {
    fun provideRepository(): LearnRepository{
        return LearnRepository.getInstance()
    }
}