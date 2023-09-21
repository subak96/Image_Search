package com.example.image_search


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface Ret_interface{
    @GET("v/search/image")
    fun search(
        @Header("Authorization") apikey: String?,
        @Query("query") query: String?,
        @Query("sort") sort: String?,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Call<ApiModel?>?
}