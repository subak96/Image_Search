package com.example.image_search

import android.telecom.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import com.example.image_search.ApiModel as ApiModel


interface Retrofit_interface{

    @GET("v2/search/image")
    fun search(
        @Header("Authorization") apikey: String?,
        @Query("query") query: String?,
        @Query("sort") sort : String?,
        @Query("page") page : Int,
        @Query("size") size : Int
    ): Call<ApiModel?>
}