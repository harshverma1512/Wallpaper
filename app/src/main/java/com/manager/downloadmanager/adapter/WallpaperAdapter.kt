package com.manager.downloadmanager.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.manager.downloadmanager.MainActivity2
import com.manager.downloadmanager.databinding.AdapterLayoutBinding
import com.manager.downloadmanager.dtos.Wallpaper


class WallpaperAdapter(
    private val context: Context
) : RecyclerView.Adapter<WallpaperAdapter.InnerViewHolder>() {
    private val response = mutableListOf<Wallpaper.Hit>()

    inner class InnerViewHolder(binding: AdapterLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val image = binding.image
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerViewHolder {
        return InnerViewHolder(
            AdapterLayoutBinding.inflate(
                LayoutInflater.from(context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: InnerViewHolder, position: Int) {
        Glide.with(context).load(response[position].largeImageURL).into(holder.image)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, MainActivity2::class.java)
            intent.putExtra(
                "ImageURL",
                response[position].largeImageURL
            )
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return response.size
    }

    fun data(it: Wallpaper) {
        response.addAll(it.hits)
    }
}