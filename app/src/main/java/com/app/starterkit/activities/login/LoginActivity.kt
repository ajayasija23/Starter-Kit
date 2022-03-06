package com.app.starterkit.activities.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.app.starterkit.BaseActivity
import com.app.starterkit.activities.signup.LoginViewModel
import com.app.starterkit.activities.signup.SignupActivity
import com.app.starterkit.databinding.ActivityLoginBinding
import com.app.starterkit.network.Repository
import com.app.starterkit.network.RetrofitClient
import com.app.starterkit.utils.MyViewModelFactory
import com.app.starterkit.utils.Status
import com.app.starterkit.utils.showDialog

class LoginActivity: BaseActivity() {
    private lateinit var binding:ActivityLoginBinding
    val apiService= RetrofitClient.apiService
    val repository= Repository(apiService)
    private val viewModel: LoginViewModel by viewModels(){
        MyViewModelFactory(repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
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
        binding.tvSignup.setOnClickListener {
            startActivity(Intent(this@LoginActivity, SignupActivity::class.java))
        }
        binding.btnLogin.setOnClickListener {
            if (isValid()){
                val map=HashMap<String,String>()
                map["email"]=binding.etEmail.text.toString()
                map["password"]=binding.etPassword.text.toString()
                viewModel.login(map)
            }
        }
    }

    fun isValid():Boolean{
        var valid=true
        return valid;
    }
}