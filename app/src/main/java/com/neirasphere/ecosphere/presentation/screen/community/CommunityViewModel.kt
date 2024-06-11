package com.neirasphere.ecosphere.presentation.screen.community

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neirasphere.ecosphere.ResultDefault
import com.neirasphere.ecosphere.domain.repository.CommunityRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class CommunityViewModel @Inject constructor(
    private val repository: CommunityRepository
): ViewModel() {

    private val _postState = MutableStateFlow(PostState())
    val postState = _postState.asStateFlow()

    fun post(content: String) = viewModelScope.launch {
        repository.post(content).collect { result ->
            when (result) {
                is ResultDefault.Loading -> {
                    _postState.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
                is ResultDefault.Success -> {
                    _postState.update {
                        it.copy(
                            isSuccess = true,
                            isLoading = false
                        )
                    }
                }
                is ResultDefault.Error -> {
                    _postState.update {
                        it.copy(
                            isError = result.error,
                            isConnectLoading = true
                        )
                    }
                }
            }
        }
    }

}
