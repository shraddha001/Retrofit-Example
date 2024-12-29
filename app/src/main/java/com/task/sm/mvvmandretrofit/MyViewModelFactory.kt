package com.task.sm.mvvmandretrofit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.task.sm.mvvmandretrofit.repository.MyRepository

class MyViewModelFactory(private val repository: MyRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyViewModel::class.java)) {
            return MyViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}