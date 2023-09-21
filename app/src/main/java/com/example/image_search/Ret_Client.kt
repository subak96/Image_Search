package com.example.image_search

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Ret_Client {

    val Api: Ret_interface
        get() = instance.create(Ret_interface::class.java)

    private val instance: Retrofit
          private get() {
              val gson = GsonBuilder().setLenient().create()
              return Retrofit.Builder()
                  .baseUrl(Constants.BASE_URL)
                  .addConverterFactory(GsonConverterFactory.create(gson))
                  .build()
        }

}