package com.task.sm.mvvmandretrofit.repository

import androidx.lifecycle.MutableLiveData
import com.task.sm.mvvmandretrofit.Charges
import com.task.sm.mvvmandretrofit.apiservice.ApiService
import com.task.sm.mvvmandretrofit.apiservice.Resource
import retrofit2.Response

class MyRepository(private val apiService: ApiService) {

    suspend fun getData(): Resource<Charges>? {
        val result = MutableLiveData<Resource<Charges>>()
        try {
            val apiKey = "Bearer sk_test_4eC39HqLyjWDarjtT1zdp7dc"
            val response: Response<Charges> = apiService.stripeCharges(apiKey)
            result.value = Resource.Loading()
            if (response.isSuccessful && response.body() != null) {
                println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>??$response")
                result.value = Resource.Success(response.body()!!)
            } else {
                println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>!!$response")
                result.value = Resource.Error("Error: ${response.message()}")
            }
        } catch (e: Exception) {
            println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>${e.message}")
            result.value = Resource.Error("Network Error: ${e.localizedMessage}")
        }

        return result.value
    }
}