package com.neirasphere.ecosphere.presentation.screen.interactivemap

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neirasphere.ecosphere.data.repository.MapRepositoryImpl
import com.neirasphere.ecosphere.domain.model.MapData
import com.neirasphere.ecosphere.domain.repository.MapRepository
import com.neirasphere.ecosphere.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(private val repository: MapRepository): ViewModel() {
    private val _tpsUiState: MutableStateFlow<UiState<List<MapData>>> = MutableStateFlow(
        UiState.Loading
    )

    val tpsUiState: StateFlow<UiState<List<MapData>>>
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