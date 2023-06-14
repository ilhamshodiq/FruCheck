package com.the12smb.capstonefrucheck.view.detail

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.marginTop
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.the12smb.capstonefrucheck.R
import com.the12smb.capstonefrucheck.data.local.model.UserPreference
import com.the12smb.capstonefrucheck.databinding.ActivityDetailBinding
import com.the12smb.capstonefrucheck.view.ViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel

    private lateinit var id : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setIdFromIntent()
        setupView()

    }

    private fun setupView() {

        detailViewModel.isLoading.observe(this) {
            showLoading(it)
        }


        detailViewModel.detail.observe(this) {

            val linearmanfaat: LinearLayout = binding.linearmanfaat
            val numTextViews = it.manfaat?.size
            for (i in 0 until numTextViews!!) {
                val textView = TextView(this)
                textView.text = "- ${it.manfaat[i]}"
                textView.textSize = 16f
                linearmanfaat.addView(textView)
            }

            val linearnutrisi: LinearLayout = binding.linearnutrisi
            val numTextViews2 = it.nutrisi?.size
            for (i in 0 until numTextViews2!!) {
                val textView = TextView(this)
                textView.text = "- ${it.nutrisi[i]}"
                textView.textSize = 16f
                linearnutrisi.addView(textView)
            }

            val linearpenyimpanan: LinearLayout = binding.linearpenyimpanan
            val numTextViews3 = it.penyimpanan?.size
            for (i in 0 until numTextViews3!!) {
                val textView = TextView(this)
                textView.text = "- ${it.penyimpanan[i]}"
                textView.textSize = 16f
                linearpenyimpanan.addView(textView)
            }


            binding.apply {
                tvFruitName.text = it.nama
                Glide.with(this@DetailActivity)
                    .load(it.photoUrl)
                    .into(ivDetailPhoto)
            }
            supportActionBar?.title = "Detail ${it.nama}"
        }

    }

    private fun setupViewModel() {
        val pref = UserPreference.getInstance(dataStore)
        detailViewModel = ViewModelProvider(this, ViewModelFactory(pref))[DetailViewModel::class.java]
    }

    private fun showLoading(state: Boolean) {
        binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }


    private fun setIdFromIntent() {
        id = intent.getStringExtra(EXTRA_ID) as String
        detailViewModel.getDetail(id)
    }



    companion object {
        const val EXTRA_ID = "extra_id"

    }


}