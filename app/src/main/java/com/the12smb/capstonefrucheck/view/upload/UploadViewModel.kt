package com.the12smb.capstonefrucheck.view.upload

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.the12smb.capstonefrucheck.data.local.model.UserPreference
import com.the12smb.capstonefrucheck.data.remote.response.Data
import com.the12smb.capstonefrucheck.data.remote.response.UploadResponse
import com.the12smb.capstonefrucheck.data.remote.retrofit.ApiConfig
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class UploadViewModel (private val pref: UserPreference) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _hasilscan = MutableLiveData<UploadResponse>()
    val hasilscan: LiveData<UploadResponse> = _hasilscan

    fun upload(photo: File) : LiveData<Boolean>{
        _isLoading.value = true
        val uploadImageRequest = MutableLiveData<Boolean>() // untuk check apakah upload berhasil

        val requestImageFile = photo.asRequestBody("image/jpeg".toMediaType())
        val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "image",
            photo.name,
            requestImageFile
        )

        val client = ApiConfig.getApiService2().uploadImage(imageMultipart)
        client.enqueue(object : Callback<UploadResponse> {
            override fun onResponse(
                call: Call<UploadResponse>,
                response: Response<UploadResponse>
            ) {
                if (response.isSuccessful) {
                    uploadImageRequest.value = response.isSuccessful
                    _isLoading.value = false
                    _hasilscan.value = response.body()
                } else {
                    _isLoading.value = false
                    Log.e(TAG, "onFailureResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<UploadResponse>, t: Throwable) {
                _isLoading.value = false
                uploadImageRequest.value = false
                Log.e(TAG, "onFailureThrowable: ${t.message}")
            }

        })
        return uploadImageRequest
    }

    companion object{
        private const val TAG = "UploadViewModel"
    }
}