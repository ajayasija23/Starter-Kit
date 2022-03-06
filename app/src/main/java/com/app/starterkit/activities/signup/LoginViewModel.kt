package com.app.starterkit.activities.signup

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.starterkit.model.ApiResponse
import com.app.starterkit.network.Repository
import com.app.starterkit.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody

class LoginViewModel(val repository: Repository):ViewModel() {

    val signupResponse=MutableLiveData<Resource<ApiResponse>>()

    fun register(map:HashMap<String,String>){
        signupResponse.value=Resource.loading(null)
        viewModelScope.launch {
            try {
                val response=repository.register(map)
                withContext(Dispatchers.Main){
                    if (response.isSuccessful){
                        signupResponse.value= Resource.success(response.body(),"")
                    }else{
                        signupResponse.value= Resource.error(null,response.message())
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main){
                    Log.d("TAG", "register: ${e.localizedMessage}")
                    signupResponse.value= Resource.error(null,e.localizedMessage)
                }
            }
        }
    }
    fun login(map:HashMap<String,String>){
        signupResponse.value=Resource.loading(null)
        viewModelScope.launch {
            try {
                val response=repository.login(map)
                withContext(Dispatchers.Main){
                    if (response.isSuccessful){
                        signupResponse.value= Resource.success(response.body(),"")
                    }else{
                        signupResponse.value= Resource.error(null,response.message())
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main){
                    Log.d("TAG", "register: ${e.localizedMessage}")
                    signupResponse.value= Resource.error(null,e.localizedMessage)
                }
            }
        }
    }
}