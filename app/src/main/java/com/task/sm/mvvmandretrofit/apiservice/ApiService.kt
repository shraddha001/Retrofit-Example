package com.task.sm.mvvmandretrofit.apiservice

import com.task.sm.mvvmandretrofit.Charges
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface ApiService {

    @GET("v1/charges")
    suspend fun stripeCharges(
        @Header("Authorization") apiKey: String
    ): Response<Charges>
}