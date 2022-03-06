package com.app.starterkit.network

import com.app.starterkit.model.ApiResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterface {

    @FormUrlEncoded
    @POST("starter_kit/register.php")
    suspend fun register(
        @FieldMap map:HashMap<String,String>
    ):Response<ApiResponse>

    @FormUrlEncoded
    @POST("starter_kit/login.php")
    suspend fun login(
        @FieldMap map:HashMap<String,String>
    ):Response<ApiResponse>
}