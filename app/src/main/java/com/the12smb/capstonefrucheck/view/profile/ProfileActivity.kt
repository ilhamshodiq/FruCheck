package com.the12smb.capstonefrucheck.view.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.the12smb.capstonefrucheck.R
import com.the12smb.capstonefrucheck.databinding.ActivityProfileBinding
import com.the12smb.capstonefrucheck.view.home.HomeActivity
import com.the12smb.capstonefrucheck.view.login.LoginActivity
import com.the12smb.capstonefrucheck.view.upload.UploadActivity

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        setupView()
        setupAction()
        setupBottomNav()
    }

//    private fun setupView() {
//        binding.tvName.text = auth.currentUser?.displayName
//    }

    private fun setupAction() {
        binding.buttonLogout.setOnClickListener {
            auth.signOut()
            Toast.makeText(this, "UNDER CONSTRUCTION", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }


    private fun setupBottomNav() {
        binding.bottomnav.selectedItemId = R.id.navigation_profile
        binding.bottomnav.itemIconTintList= null

        binding.bottomnav.setOnItemSelectedListener{
            when(it.itemId){
                R.id.navigation_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
                R.id.navigation_upload -> {
                    startActivity(Intent(this, UploadActivity::class.java))
                    overridePendingTransition(0, 0)
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