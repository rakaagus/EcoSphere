package com.neirasphere.ecosphere.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.neirasphere.ecosphere.data.LearnRepository
import com.neirasphere.ecosphere.ui.screen.education.FifthEducationViewModel
import com.neirasphere.ecosphere.ui.screen.education.FirstEducationViewModel
import com.neirasphere.ecosphere.ui.screen.education.FourthEducationViewModel
import com.neirasphere.ecosphere.ui.screen.education.SecondEducationViewModel
import com.neirasphere.ecosphere.ui.screen.education.ThirdEducationViewModel
import com.neirasphere.ecosphere.ui.screen.home.HomeViewModel

class ViewModelFactory(val repository: LearnRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}