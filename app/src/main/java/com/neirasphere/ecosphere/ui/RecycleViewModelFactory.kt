package com.neirasphere.ecosphere.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.neirasphere.ecosphere.data.RecycleRepository
import com.neirasphere.ecosphere.ui.screen.recycle.FirstRecycleViewModel
import com.neirasphere.ecosphere.ui.screen.recycle.RecycleViewModel
import com.neirasphere.ecosphere.ui.screen.recycle.SecondRecycleViewModel
import java.lang.IllegalArgumentException

class RecycleViewModelFactory(
    val repository : RecycleRepository
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T{
        if(modelClass.isAssignableFrom(RecycleViewModel::class.java)){
            return RecycleViewModel(repository) as T
        } else if(modelClass.isAssignableFrom(FirstRecycleViewModel::class.java)){
            return FirstRecycleViewModel(repository) as T
        } else if(modelClass.isAssignableFrom(SecondRecycleViewModel::class.java)){
            return SecondRecycleViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

}