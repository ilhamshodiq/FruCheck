package com.the12smb.capstonefrucheck.view.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatDelegate
import com.bumptech.glide.Glide
import com.the12smb.capstonefrucheck.R
import com.the12smb.capstonefrucheck.view.home.HomeActivity
import com.the12smb.capstonefrucheck.view.login.LoginActivity

@SuppressLint("CustomSplashScreen")
@Suppress("DEPRECATION")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()
        setupView()

        val imageView: ImageView = findViewById(R.id.iv_logo)
        Glide.with(this)
            .load(R.drawable.splash)
            .into(imageView)

        Handler().postDelayed({
            startActivity(
                Intent(this, LoginActivity::class.java)
            )
            finish()
        }, 1500)
    }
    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

}