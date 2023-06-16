package com.the12smb.capstonefrucheck.data.remote.retrofit

import com.the12smb.capstonefrucheck.data.remote.response.BuahResponse
import com.the12smb.capstonefrucheck.data.remote.response.DetailBuahResponse
import com.the12smb.capstonefrucheck.data.remote.response.UploadResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {
    @GET("buah")
    fun getListBuah(
    ): Call<BuahResponse>

    @GET("/buah/{id}")
    fun getDetailBuah(@Path("id") id: String): Call<DetailBuahResponse>

}

interface ApiService2 {
    @POST("upload")
    @Multipart
    fun uploadImage(
        @Part image: MultipartBody.Part
    ): Call<UploadResponse>
//    @POST("upload")
//    fun uploadImage()
}