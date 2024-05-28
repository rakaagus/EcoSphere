package com.neirasphere.ecosphere.presentation.screen.interactivemap.detailtps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neirasphere.ecosphere.data.repository.MapRepositoryImpl
import com.neirasphere.ecosphere.domain.model.MapData
import com.neirasphere.ecosphere.domain.repository.MapRepository
import com.neirasphere.ecosphere.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailTpsViewModel @Inject constructor(private val repository: MapRepository) : ViewModel(){
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