package com.task.sm.mvvmandretrofit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.sm.mvvmandretrofit.apiservice.Resource
import com.task.sm.mvvmandretrofit.repository.MyRepository
import kotlinx.coroutines.launch

class MyViewModel(val repository: MyRepository) : ViewModel() {

    private val chargesState = MutableLiveData<Resource<Charges>>()
    var isOffline = false

    fun getChargesState() = chargesState

    fun getCharges() {
        if (isOffline) {
            chargesState.postValue(Resource.Error("You are offline"))
        }
        viewModelScope.launch {
            println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
            chargesState.postValue(repository.getData())
        }
    }

}