package com.the12smb.capstonefrucheck.view.upload

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.the12smb.capstonefrucheck.R
import com.the12smb.capstonefrucheck.databinding.ActivityUploadBinding
import com.the12smb.capstonefrucheck.view.home.HomeActivity
import com.the12smb.capstonefrucheck.view.profile.ProfileActivity

class UploadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUploadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
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
                    true
                }
                R.id.navigation_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }

}