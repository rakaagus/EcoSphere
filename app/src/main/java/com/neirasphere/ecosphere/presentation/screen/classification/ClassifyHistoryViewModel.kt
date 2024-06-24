package com.neirasphere.ecosphere.presentation.screen.classification

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neirasphere.ecosphere.data.local.entities.ClassifyHistory
import com.neirasphere.ecosphere.domain.repository.ClassifyHistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClassifyHistoryViewModel @Inject constructor(

    private val repo: ClassifyHistoryRepository

) : ViewModel() {

    private var classifyHistory by mutableStateOf(ClassifyHistory(0, "", ""))

    private val _allClassifyHistory = MutableStateFlow<List<ClassifyHistory>>(emptyList())
    val allClassifyHistory = repo.getAllClassifyHistory()

    private val _organicCount = MutableStateFlow(0)
    val organicCount = _organicCount.asStateFlow()

    private val _anOrganicCount = MutableStateFlow(0)
    val anOrganicCount = _anOrganicCount.asStateFlow()

    private val _totalCount = MutableStateFlow(0)
    val totalCount = _totalCount.asStateFlow()

    private val _requestStoragePermissionEvent = MutableStateFlow(false)
    val requestStoragePermissionEvent: StateFlow<Boolean> = _requestStoragePermissionEvent

    fun requestStoragePermission() {
        _requestStoragePermissionEvent.value = true
    }

    init {
        viewModelScope.launch {
            repo.getAllClassifyHistory().collect { classifyHistoryList ->
                _allClassifyHistory.value = classifyHistoryList
                calculateCounts(classifyHistoryList)
            }
        }
    }
    fun getClassifyHistoryById(id: Int) = viewModelScope.launch {
        classifyHistory = repo.getClassifyHistoryById(id = id)
    }

    fun insertHistory(history: ClassifyHistory) {
        viewModelScope.launch {
            repo.addClassifyHistory(history)
        }
    }

    private fun calculateCounts(allHistory: List<ClassifyHistory>) {
        val organic = allHistory.count {
            it.title == "Paper" || it.title == "Organic"
        }
        val anOrganic = allHistory.size - organic

        _organicCount.value = organic
        _anOrganicCount.value = anOrganic
        _totalCount.value = allHistory.size
    }
}