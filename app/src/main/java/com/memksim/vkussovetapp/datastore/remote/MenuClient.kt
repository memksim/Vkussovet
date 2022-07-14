package com.memksim.vkussovetapp.datastore.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MenuClient {
    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit{
        if(retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl("https://vkus-sovet.ru")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return retrofit!!
    }
}