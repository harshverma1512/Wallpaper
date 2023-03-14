package com.manager.downloadmanager.module

import com.manager.downloadmanager.dtos.Wallpaper
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object APIResponse {

    //https://pixabay.com/api/?key=34359826-d820ab52f76bf617e44b60670&q=Nature&image_type=photo&pretty=true

    val retrofit: APIRequest = Retrofit.Builder()
        .baseUrl("https://pixabay.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(APIRequest::class.java)

    interface APIRequest {
        @GET("/api/?key=34359826-d820ab52f76bf617e44b60670&q=Space&image_type=photo&pretty=true")
        fun getData(): Call<Wallpaper>


        @GET("/api/?key=34359826-d820ab52f76bf617e44b60670")
        fun setData(
            @Query("q") query: String,
            @Query("image_type") imageType: String,
            @Query("pretty") pretty: Boolean
        ): Call<Wallpaper>
    }
}