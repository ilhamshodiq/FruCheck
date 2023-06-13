package com.the12smb.capstonefrucheck.view.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.the12smb.capstonefrucheck.data.remote.response.BuahItem
import com.the12smb.capstonefrucheck.data.remote.response.BuahResponse
import com.the12smb.capstonefrucheck.data.remote.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel: ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _listBuah = MutableLiveData<List<BuahItem>?>()
    val listBuah: LiveData<List<BuahItem>?> = _listBuah

    fun getStories() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getListBuah()
        client.enqueue(object : Callback<BuahResponse> {
            override fun onResponse(
                call: Call<BuahResponse>,
                response: Response<BuahResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listBuah.value = response.body()?.buah
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<BuahResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    companion object{
        const val TAG = "HomeViewModel"
    }
}