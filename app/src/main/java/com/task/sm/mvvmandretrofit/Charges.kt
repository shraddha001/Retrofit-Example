package com.task.sm.mvvmandretrofit

data class Charges(
    var status: String,
    val data: List<ChargesData>
): java.io.Serializable

data class ChargesData(
    val id: String?,
    val amount: Int
): java.io.Serializable