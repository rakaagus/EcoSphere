package com.neirasphere.ecosphere.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.neirasphere.ecosphere.data.repository.LearnRepositoryImpl
import com.neirasphere.ecosphere.presentation.screen.home.HomeViewModel

class ViewModelFactory(val repository: LearnRepositoryImpl): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}