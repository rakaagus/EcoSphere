package com.neirasphere.ecosphere.presentation.screen.classification

import com.neirasphere.ecosphere.data.remote.response.ClassifyResult

data class ClassificationState(
    val error: String? = null,
    val loading: Boolean = false,
    val success: Boolean = false,
    val result: ClassifyResult? = null
)
