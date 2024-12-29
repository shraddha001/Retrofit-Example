package com.task.sm.mvvmandretrofit

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.task.sm.mvvmandretrofit.repository.MyRepository

class MyViewModelFactory(private val repository: MyRepository, val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyViewModel::class.java)) {
            return MyViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(NetworkViewModel::class.java)) {
            return NetworkViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}