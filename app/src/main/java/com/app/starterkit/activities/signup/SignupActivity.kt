package com.app.starterkit.activities.signup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.app.starterkit.BaseActivity
import com.app.starterkit.activities.login.LoginActivity
import com.app.starterkit.databinding.ActivitySignupBinding
import com.app.starterkit.network.Repository
import com.app.starterkit.network.RetrofitClient
import com.app.starterkit.utils.MyViewModelFactory
import com.app.starterkit.utils.Status
import com.app.starterkit.utils.showDialog

class SignupActivity: BaseActivity() {
    private lateinit var binding:ActivitySignupBinding
    val apiService=RetrofitClient.apiService
    val repository=Repository(apiService)
    private val viewModel:LoginViewModel by viewModels(){
        MyViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listeners()
        bindObservers()
    }

    private fun bindObservers() {
        viewModel.signupResponse.observe(this){
            when(it.status){
                Status.LOADING->{
                    Toast.makeText(this, "Processing.....", Toast.LENGTH_SHORT).show()
                }
                Status.SUCCEESS->{
                    it.data!!.message.showDialog(this)
                }
                Status.ERROR->{
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun listeners() {
        binding.tvLogin.setOnClickListener {
            startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
            finish()
        }
        binding.btnSignup.setOnClickListener {
            if (isValid()){
                val map=HashMap<String,String>()
                map["firstName"]=binding.etFirstName.text.toString()
                map["lastName"]=binding.etLastName.text.toString()
                map["phone"]=binding.etMobile.text.toString()
                map["email"]=binding.etEmail.text.toString()
                map["password"]=binding.etPassword.text.toString()
                viewModel.register(map)
            }
        }
    }

    fun isValid():Boolean{
        var valid=true
        return valid;
    }
}