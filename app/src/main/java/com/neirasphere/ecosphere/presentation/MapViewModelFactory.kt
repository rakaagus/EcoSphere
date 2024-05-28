package com.neirasphere.ecosphere.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.neirasphere.ecosphere.data.MapRepository
import com.neirasphere.ecosphere.presentation.screen.interactivemap.MapViewModel
import com.neirasphere.ecosphere.presentation.screen.interactivemap.detailtps.DetailTpsViewModel

class MapViewModelFactory(val repository: MapRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MapViewModel::class.java)) {
            return MapViewModel(repository) as T
        }else if(modelClass.isAssignableFrom(DetailTpsViewModel::class.java)){
            return DetailTpsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}