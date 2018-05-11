package com.santander.android.util

class Resource<T>(
        val status: Status,
        var data: T? = null,
        var message: String? = null
) {

    enum class Status {
        SUCCESS, ERROR, LOADING
    }

    fun isLoading(): Boolean {
        return status == Status.LOADING
    }

    fun hasSucceeded(): Boolean {
        return status == Status.SUCCESS
    }

    fun hasFailed(): Boolean {
        return status == Status.ERROR
    }

    companion object {

        fun <T> loading(data: T? = null, msg: String? = null): Resource<T> {
            return Resource(Status.LOADING, data, msg)
        }

        fun <T> success(data: T? = null, msg: String? = null): Resource<T> {
            return Resource(Status.SUCCESS, data, msg)
        }

        fun <T> error(msg: String? = null, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

    }

}