package com.neirasphere.ecosphere.ui.screen.interactivemap.detailtps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neirasphere.ecosphere.data.MapRepository
import com.neirasphere.ecosphere.model.MapData
import com.neirasphere.ecosphere.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailTpsViewModel(private val repository: MapRepository) : ViewModel(){
    private val _detailTpsUiState: MutableStateFlow<UiState<MapData>> = MutableStateFlow(
        UiState.Loading
    )

    val detailTpsUiState : StateFlow<UiState<MapData>>
        get() = _detailTpsUiState

    fun getDetailTps(tpsId: Long){
        viewModelScope.launch {
            _detailTpsUiState.value = UiState.Loading
            _detailTpsUiState.value = UiState.Success(repository.getDetailTps(tpsId))
        }
    }
}