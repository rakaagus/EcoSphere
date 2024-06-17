package com.neirasphere.ecosphere.data

sealed class CommunityResult<out R> {
    data class Success<out T>(val data: T) : CommunityResult<T>()
    data class Error(val message: String? = null) : CommunityResult<Nothing>()
    data class Loading<out T>(val data: T? = null) : CommunityResult<T>()
}