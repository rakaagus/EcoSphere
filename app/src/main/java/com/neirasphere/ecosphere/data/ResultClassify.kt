package com.neirasphere.ecosphere.data

sealed class ResultClassify<out R> {
    data class Loading<out T>(val data: T? = null) : ResultClassify<T>()
    data class Success<out T>(val data: T) : ResultClassify<T>()
    data class Error(val message: String? = null) : ResultClassify<Nothing>()
}