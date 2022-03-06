package com.app.starterkit

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.lifecycle.lifecycleScope
import com.app.starterkit.activities.login.LoginActivity
import com.app.starterkit.databinding.ActivitySplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity() {
    private lateinit var binding:ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        hideStatusBar()
        val imageAnim= AnimationUtils.loadAnimation(this,R.anim.translate_image)
        val textAnim= AnimationUtils.loadAnimation(this,R.anim.fade_up_text)
        binding.ivLogo.startAnimation(imageAnim)
        binding.textView.startAnimation(textAnim)
        lifecycleScope.launch {
            delay(3500)
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            finish()
        }
    }
}