package com.the12smb.capstonefrucheck.view.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.the12smb.capstonefrucheck.R
import com.the12smb.capstonefrucheck.data.remote.response.BuahItem
import com.the12smb.capstonefrucheck.databinding.ActivityHomeBinding
import com.the12smb.capstonefrucheck.view.profile.ProfileActivity
import com.the12smb.capstonefrucheck.view.upload.UploadActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupView()
        setupBottomNav()
    }


    private fun setupView() {

        homeViewModel.getStories()
        val layoutManager = GridLayoutManager(this, 2)
        binding.rvContent.layoutManager = layoutManager

        val itemDecoration = DividerItemDecoration(this, layoutManager.layoutDirection)
        val itemDecoration2 = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvContent.addItemDecoration(itemDecoration)
        binding.rvContent.addItemDecoration(itemDecoration2)

        homeViewModel.listBuah.observe(this) { listBuah ->
            binding.rvContent.adapter = showRecyclerView(listBuah)
        }

        homeViewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

        private fun showLoading(state: Boolean) {
            binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
        }

        private fun showRecyclerView(list: List<BuahItem>?): BuahAdapter {
            val buahList = ArrayList<BuahItem>()

            list?.let {
                buahList.clear()
                buahList.addAll(it)
            }

            return BuahAdapter(buahList)
        }

    private fun setupBottomNav() {
        binding.bottomnav.selectedItemId = R.id.navigation_home
        binding.bottomnav.itemIconTintList= null

        binding.bottomnav.setOnItemSelectedListener{
            when(it.itemId){
                R.id.navigation_home -> {
                    true
                }
                R.id.navigation_upload -> {
                    startActivity(Intent(this, UploadActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
                R.id.navigation_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
                else -> false
            }
        }
    }




}