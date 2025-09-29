package com.NeutralAMG.URLShortener.Common.Util

sealed class AppResult<out Data> {
    data class Success<out Data>(val data: Data,val message: String ="Operation was successful") : AppResult<Data>()
    data class SuccessWithoutResult<out Data>(val message: String="Operation was successful") : AppResult<Data>()
    data class Error(val message: String="Operation was unsuccessful"): AppResult<Nothing>()

    val isSuccess get() = this is Success
    val isSuccessWithoutResult get() = this is SuccessWithoutResult
    val isError get() = this is Error
}