package com.memksim.vkussovetapp.datastore.remote.api

import com.memksim.vkussovetapp.model.MenuRequest
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface MenuApi {

    @GET("/api/getMenu.php")
    fun getMenu(): Call<MenuRequest>

}