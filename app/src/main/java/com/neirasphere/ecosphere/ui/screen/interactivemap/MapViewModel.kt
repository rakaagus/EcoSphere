package com.neirasphere.ecosphere.ui.screen.interactivemap

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neirasphere.ecosphere.data.MapRepository
import com.neirasphere.ecosphere.domain.model.MapData
import com.neirasphere.ecosphere.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MapViewModel(private val repository: MapRepository): ViewModel() {
    private val _tpsUiState: MutableStateFlow<UiState<List<com.neirasphere.ecosphere.domain.model.MapData>>> = MutableStateFlow(
        UiState.Loading
    )

    val tpsUiState: StateFlow<UiState<List<com.neirasphere.ecosphere.domain.model.MapData>>>
        get() = _tpsUiState

    fun getAllTpsData(){
        viewModelScope.launch {
            repository.getAllTpsData()
                .catch {
                    _tpsUiState.value = UiState.Error(it.message.toString())
                }
                .collect{
                    _tpsUiState.value = UiState.Success(it)
                }
        }
    }
}