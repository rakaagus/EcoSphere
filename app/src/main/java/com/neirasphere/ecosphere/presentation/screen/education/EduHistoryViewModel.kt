package com.neirasphere.ecosphere.presentation.screen.education

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neirasphere.ecosphere.domain.model.EduHistory
import com.neirasphere.ecosphere.domain.repository.EduHistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EduHistoryViewModel @Inject constructor(
    private val repo: EduHistoryRepository
) : ViewModel() {

    var eduHistory by mutableStateOf(EduHistory(0, "", -1))

    val eduHistoryList = repo.getEduHistory()

    fun getEduHistoryById(id: Int) = viewModelScope.launch {
        eduHistory = repo.getEduHistoryById(id)
    }

    fun addEduHistory(eduHistory: EduHistory) = viewModelScope.launch {
        repo.addEduHistory(eduHistory)
    }

}