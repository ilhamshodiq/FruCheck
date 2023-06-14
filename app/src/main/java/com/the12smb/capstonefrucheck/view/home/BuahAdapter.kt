package com.the12smb.capstonefrucheck.view.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.the12smb.capstonefrucheck.data.remote.response.BuahItem
import com.the12smb.capstonefrucheck.databinding.ItemCardviewBinding
import com.the12smb.capstonefrucheck.view.detail.DetailActivity

class BuahAdapter(private val listBuah: List<BuahItem>) :
    RecyclerView.Adapter<BuahAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemCardviewBinding) : RecyclerView.ViewHolder(binding.root) {
        val ivItemPhoto: ImageView = binding.ivItemPhoto
        val tvItemName: TextView = binding.tvItemName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCardviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val buah = listBuah[position]
        holder.tvItemName.text = buah.nama
        Glide.with(holder.itemView.context)
            .load(buah.photoUrl)
            .into(holder.ivItemPhoto)

        holder.itemView.setOnClickListener{
            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra(DetailActivity.EXTRA_ID, buah.id.toString())// intent id
            holder.itemView.context.startActivity(intentDetail)
        }
    }

    override fun getItemCount(): Int {
        return listBuah.size
    }

}