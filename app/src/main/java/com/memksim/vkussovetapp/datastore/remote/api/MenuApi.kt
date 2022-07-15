package com.memksim.vkussovetapp.datastore.remote.api

import com.memksim.vkussovetapp.model.MenuRequest
import com.memksim.vkussovetapp.model.SubMenuRequest
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MenuApi {

    @GET("/api/getMenu.php")
    fun getMenu(): Call<MenuRequest>

    @GET("/api/getSubMenu.php")
    fun getSubMenu(@Query("menuID") menuID: String): Call<SubMenuRequest>

}