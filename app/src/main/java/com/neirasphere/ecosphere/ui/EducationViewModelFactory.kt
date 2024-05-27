package com.neirasphere.ecosphere.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.neirasphere.ecosphere.data.EducationRepository
import com.neirasphere.ecosphere.ui.screen.education.EducationViewModel
import com.neirasphere.ecosphere.ui.screen.education.FifthEducationViewModel
import com.neirasphere.ecosphere.ui.screen.education.FirstEducationViewModel
import com.neirasphere.ecosphere.ui.screen.education.FourthEducationViewModel
import com.neirasphere.ecosphere.ui.screen.education.SecondEducationViewModel
import com.neirasphere.ecosphere.ui.screen.education.ThirdEducationViewModel

class EducationViewModelFactory(val repository: EducationRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(EducationViewModel::class.java)) {
            return EducationViewModel(repository) as T
        } else if(modelClass.isAssignableFrom(FirstEducationViewModel::class.java)){
            return FirstEducationViewModel(repository) as T
        } else if(modelClass.isAssignableFrom(SecondEducationViewModel::class.java)){
            return SecondEducationViewModel(repository) as T
        } else if(modelClass.isAssignableFrom(ThirdEducationViewModel::class.java)){
            return ThirdEducationViewModel(repository) as T
        } else if(modelClass.isAssignableFrom(FourthEducationViewModel::class.java)) {
            return FourthEducationViewModel(repository) as T
        } else if(modelClass.isAssignableFrom(FifthEducationViewModel::class.java)) {
            return FifthEducationViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}