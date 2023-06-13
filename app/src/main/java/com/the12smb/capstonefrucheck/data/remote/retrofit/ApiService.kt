package com.the12smb.capstonefrucheck.data.remote.retrofit

import com.the12smb.capstonefrucheck.data.remote.response.BuahResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {
    @GET("buah")
    fun getListBuah(
    ): Call<BuahResponse>

}