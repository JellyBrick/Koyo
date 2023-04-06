package be.zvz.koyo.dto

data class Response<T>(
    val retcode: Int,
    val message: String,
    val data: T,
)
