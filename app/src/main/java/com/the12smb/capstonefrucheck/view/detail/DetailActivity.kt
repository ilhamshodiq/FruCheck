package com.the12smb.capstonefrucheck.view.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.marginTop
import com.the12smb.capstonefrucheck.R
import com.the12smb.capstonefrucheck.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()

        val linearmanfaat: LinearLayout = binding.linearmanfaat

        val numTextViews = 5 // Jumlah TextView yang ingin ditambahkan

        for (i in 0 until numTextViews) {
            val textView = TextView(this)
            textView.text = "TextView ${i + 1}" // Mengatur teks
            textView.textSize = 14f // Mengatur ukuran teks


            linearmanfaat.addView(textView) // Menambahkan TextView ke dalam container
        }
    }

    private fun setupView() {
        supportActionBar?.setTitle("Detail Buah")//temp
    }


}