package com.neirasphere.ecosphere.presentation.screen.classification



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neirasphere.ecosphere.data.ResultClassify
import com.neirasphere.ecosphere.data.repository.ClassificationRepositoryImpl
import com.neirasphere.ecosphere.domain.repository.ClassificationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ClassificationViewModel @Inject constructor(
    private val classificationRepository: ClassificationRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ClassificationState())
    val state = _state.asStateFlow()

    fun classifyTrash(file: File) = viewModelScope.launch {
        classificationRepository.classifyTrash(file).collect{ result ->
            when(result){
                is ResultClassify.Error -> {
                    _state.update {
                        it.copy(
                            loading = false,
                            error = result.message
                        )
                    }
                }
                is ResultClassify.Loading -> {
                    _state.update {
                        it.copy(
                            loading = true,
                        )
                    }
                }
                is ResultClassify.Success -> {
                    _state.update {
                        it.copy(
                            loading = false,
                            result = result.data,
                            success = true
                        )
                    }
                }
            }
        }
    }
    fun resetState(){
        _state.update {
            ClassificationState()
        }
    }
}