package com.app.starterkit.network

import com.app.starterkit.model.ApiResponse
import okhttp3.ResponseBody
import retrofit2.Response

class Repository(val apiService:ApiInterface) {
    suspend fun register(map:HashMap<String,String>):Response<ApiResponse>{
        return apiService.register(map)
    }
    suspend fun login(map:HashMap<String,String>):Response<ApiResponse>{
        return apiService.login(map)
    }
}