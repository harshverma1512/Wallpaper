package com.manager.downloadmanager

import android.annotation.SuppressLint
import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.manager.downloadmanager.databinding.ActivityMain2Binding
import java.io.InputStream
import java.net.URL

class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        Glide.with(this).load(intent.extras?.getString("ImageURL")).into(binding.imge)
        binding.ApplyWallpaper.setOnClickListener {
            DownloadImageTask().execute(intent.extras?.getString("ImageURL"))
        }
    }

    @SuppressLint("StaticFieldLeak")
    private inner class DownloadImageTask : AsyncTask<String, Void, Bitmap?>() {

        @Deprecated("Deprecated in Java")
        override fun doInBackground(vararg urls: String): Bitmap? {
            val imageURL = urls[0]
            try {
                val inputStream: InputStream = URL(imageURL).openStream()
                return BitmapFactory.decodeStream(inputStream)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }

        @Deprecated("Deprecated in Java")
        override fun onPostExecute(result: Bitmap?) {
            if (result != null) {
                // Set the downloaded image as the wallpaper
                WallpaperManager.getInstance(applicationContext).setBitmap(result)
                Toast.makeText(applicationContext, "Wallpaper set successfully", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(applicationContext, "Error downloading image", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}