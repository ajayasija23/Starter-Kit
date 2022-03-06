package com.app.starterkit.utils

data class Resource <out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?, message: String?): Resource<T> = Resource(status = Status.SUCCEESS, data = data, message = message)
        fun <T> error(data: T?, message: String?): Resource<T> =
            Resource(status = Status.ERROR, data = data, message = message)
        fun <T> loading(data: T?): Resource<T> = Resource(status = Status.LOADING, data = data, message = null)
    }
}