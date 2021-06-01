package com.example.homework10

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class CountriesViewModel : ViewModel() {

    private val countriesLiveData =
        MutableLiveData<List<Countries>>().apply { mutableListOf<Countries>() }
    val _countriesLiveData: LiveData<List<Countries>> = countriesLiveData

    private val liveDataLoading = MutableLiveData<Boolean>()
    val _liveDataLoading: LiveData<Boolean> = liveDataLoading

    fun init() {
        CoroutineScope(Dispatchers.IO).launch {
            countries()
        }
    }

    private suspend fun countries() {
        try {
            val result = RetrofitService.retrofitService().getCountry()
            if (result.isSuccessful) {
                val countriesItems = result.body()
                countriesLiveData.postValue(countriesItems)
            }
        } catch (e: Exception) {
            Log.d("mainlog", "${e.message}")
        }
        liveDataLoading.postValue(false)
    }
}