package com.memksim.vkussovetapp.model.repos

import android.util.Log
import com.memksim.vkussovetapp.TAG
import com.memksim.vkussovetapp.datastore.remote.MenuClient
import com.memksim.vkussovetapp.datastore.remote.api.MenuApi
import com.memksim.vkussovetapp.model.MenuRequest
import com.memksim.vkussovetapp.view.splashScreen.AppCallback
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

    private val api = MenuClient.getClient().create(MenuApi::class.java)

    fun loadData(appCallback: AppCallback){

        Log.d(TAG, "loadData: Test")
        api.getMenu()
            .enqueue(object: Callback<MenuRequest>{
                override fun onResponse(
                    call: Call<MenuRequest>,
                    response: Response<MenuRequest>
                ) {
                    Log.d(TAG, "onResponse: ${response.isSuccessful}")
                    if(response.body() == null){
                        appCallback.onError()
                    }else{
                        appCallback.onLoaded(response.body()!!.menuList)
                        Log.d(TAG, "onResponse: ${response.body()!!.menuList[3]}")
                    }
                }

                override fun onFailure(call: Call<MenuRequest>, t: Throwable) {
                    Log.e(TAG, "onFailure: $t", )
                    appCallback.onError(/*TODO*/)
                }
            })
    }

    fun loadImages(urls: List<String>): List<RequestCreator>{
        val resultList = arrayListOf<RequestCreator>()
        for(i in urls){
            resultList.add(Picasso.get().load("https://vkus-sovet.ru/$i"))
        }

        return resultList
    }

    fun getMenu(callback: com.memksim.vkussovetapp.viewmodel.Callback){
        api.getMenu()
            .enqueue(object: Callback<MenuRequest>{
                override fun onResponse(
                    call: Call<MenuRequest>,
                    response: Response<MenuRequest>
                ) {
                    Log.d(TAG, "onResponse: ${response.isSuccessful}")
                    if(response.body() == null){
                        callback.onError()
                    }else{
                        callback.onSuccess(menu = response.body()!!.menuList)
                    }
                }

                override fun onFailure(call: Call<MenuRequest>, t: Throwable) {
                    Log.e(TAG, "onFailure: $t", )
                    callback.onError()
                }

            })
    }

}