package com.neirasphere.ecosphere.presentation.screen.home

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neirasphere.ecosphere.data.repository.LearnRepositoryImpl
import com.neirasphere.ecosphere.domain.model.CategoryLearn
import com.neirasphere.ecosphere.domain.repository.AppRepository
import com.neirasphere.ecosphere.domain.repository.LearnRepository
import com.neirasphere.ecosphere.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: LearnRepository,
    private val appRepository: AppRepository
) : ViewModel() {
    private val _categoryLearn: MutableStateFlow<UiState<List<CategoryLearn>>> = MutableStateFlow(
        UiState.Loading
    )

    val categoryLearn: StateFlow<UiState<List<CategoryLearn>>>
        get() = _categoryLearn

    private val _user = MutableStateFlow(HomeState())
    val user = _user.asStateFlow()

    fun getLearnHome() {
        viewModelScope.launch {
            repository.getAllHomeLearnCategory()
                .catch {
                    _categoryLearn.value = UiState.Error(it.message.toString())
                }
                .collect { data ->
                    _categoryLearn.value = UiState.Success(data)
                }
        }
    }

    fun dataUser() = viewModelScope.launch {
        appRepository.getSessionUser().collect{ result ->
            _user.update {
                it.copy(
                    dataUser = result
                )
            }
        }
    }
}