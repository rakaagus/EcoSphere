package com.neirasphere.ecosphere

sealed class ResultDefault<out R> private constructor() {
        data class Success<out T>(val data: T): ResultDefault<T>()
        data class Error(val error: String) : ResultDefault<Nothing>()
        object Loading: ResultDefault<Nothing>()
}