package com.the12smb.capstonefrucheck.view.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.the12smb.capstonefrucheck.data.local.model.UserPreference
import com.the12smb.capstonefrucheck.data.remote.response.Data
import com.the12smb.capstonefrucheck.data.remote.response.DetailBuahResponse
import com.the12smb.capstonefrucheck.data.remote.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(private val pref: UserPreference) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    private val _detail = MutableLiveData<Data>()
    val detail: LiveData<Data> = _detail

    fun getDetail(id: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailBuah(id)
        client.enqueue(object : Callback<DetailBuahResponse> {
            override fun onResponse(
                call: Call<DetailBuahResponse>,
                response: Response<DetailBuahResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _detail.value = response.body()?.data
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetailBuahResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }


    companion object {
        private const val TAG = "DetailViewModel"
    }
}