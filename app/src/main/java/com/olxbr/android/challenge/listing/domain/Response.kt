package com.olxbr.android.challenge.listing.domain

sealed class Response<out T>{
    data class Success<T>(val data: T): Response<T>()
    data class Error(val exception: Exception): Response<Nothing>()
}
