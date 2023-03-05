package icu.ofatal.yitai.data.model

data class ResWrapper<T>(
    val code: Int,
    val message: String,
    val data: T
)