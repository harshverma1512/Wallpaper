package com.manager.downloadmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.manager.downloadmanager.adapter.WallpaperAdapter
import com.manager.downloadmanager.databinding.ActivityMainBinding
import com.manager.downloadmanager.dtos.Wallpaper
import com.manager.downloadmanager.module.APIResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.gifImageView.visibility = View.VISIBLE

        APIResponse.retrofit.getData().enqueue(object : Callback<Wallpaper> {
            override fun onResponse(
                call: Call<Wallpaper>, response: Response<Wallpaper>
            ) {
                Log.d("response_text", response.body().toString())
                binding.recyclerview.setHasFixedSize(true)
                val adapter = WallpaperAdapter(
                    this@MainActivity
                )
                response.body()?.let { adapter.data(it) }
                binding.gifImageView.visibility = View.GONE
                binding.recyclerview.layoutManager =
                    StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                binding.recyclerview.adapter = adapter
            }

            override fun onFailure(call: Call<Wallpaper>, t: Throwable) {
                Log.d("WallpaperFail", t.message.toString())
            }
        })

        binding.go.setOnClickListener {
            binding.recyclerview.visibility = View.GONE
            binding.gifImageView.visibility = View.VISIBLE
            APIResponse.retrofit.setData(
                query = binding.search.text.toString(),
                imageType = "photo",
                pretty = true
            ).enqueue(object : Callback<Wallpaper> {
                override fun onResponse(call: Call<Wallpaper>, response: Response<Wallpaper>) {
                    Log.d("responseCheck", response.body().toString())
                    binding.recyclerview.setHasFixedSize(true)
                    val adapter = WallpaperAdapter(
                        this@MainActivity
                    )
                    response.body()?.let { adapter.data(it) }
                    binding.gifImageView.visibility = View.GONE
                    binding.recyclerview.visibility = View.VISIBLE
                    binding.recyclerview.layoutManager =
                        StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                    binding.recyclerview.adapter = adapter
                }

                override fun onFailure(call: Call<Wallpaper>, t: Throwable) {
                    Log.d("checkFailure", t.message.toString())
                }
            })
        }
    }
}