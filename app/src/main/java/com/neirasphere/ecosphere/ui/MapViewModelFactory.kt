package com.neirasphere.ecosphere.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.neirasphere.ecosphere.data.MapRepository
import com.neirasphere.ecosphere.ui.screen.home.HomeViewModel
import com.neirasphere.ecosphere.ui.screen.interactivemap.MapViewModel

class MapViewModelFactory(val repository: MapRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MapViewModel::class.java)) {
            return MapViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}