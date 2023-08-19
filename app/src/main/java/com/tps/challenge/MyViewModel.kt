package com.tps.challenge

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tps.challenge.network.TPSCoroutineService
import com.tps.challenge.network.model.StoreResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyViewModel(private val service: TPSCoroutineService): ViewModel() {
    private val _stores = MutableLiveData<List<StoreResponse>>()
    val stores: LiveData<List<StoreResponse>> = _stores

    fun getStoreFeed(latitude: Double, longitude: Double) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = service.getStoreFeed(latitude, longitude)
            // delay(2000)
            withContext(Dispatchers.Main) {
                _stores.postValue(result)
            }
        }
    }
}