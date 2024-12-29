package com.task.sm.mvvmandretrofit

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class NetworkViewModel(application: Application) : AndroidViewModel(application) {

    private val _isNetworkAvailable = MutableLiveData<Boolean>()
    val isNetworkAvailable: LiveData<Boolean> get() = _isNetworkAvailable

    init {
        // Initialize the network status when the ViewModel is created
        checkNetworkStatus()
    }

    // Function to check the network status
    private fun checkNetworkStatus() {
        val connectivityManager =
            getApplication<Application>().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
        _isNetworkAvailable.value = networkInfo?.isConnected == true
    }
}