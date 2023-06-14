package com.the12smb.capstonefrucheck.view.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.the12smb.capstonefrucheck.R
import com.the12smb.capstonefrucheck.databinding.ActivityProfileBinding
import com.the12smb.capstonefrucheck.view.home.HomeActivity
import com.the12smb.capstonefrucheck.view.upload.UploadActivity

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNav()
    }

    private fun setupBottomNav() {
        binding.bottomnav.selectedItemId = R.id.navigation_home

        binding.bottomnav.setOnItemSelectedListener{
            when(it.itemId){
                R.id.navigation_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    true
                }
                R.id.navigation_upload -> {
                    startActivity(Intent(this, UploadActivity::class.java))
                    true
                }
                R.id.navigation_profile -> {
                    true
                }
                else -> false
            }
        }
    }
}