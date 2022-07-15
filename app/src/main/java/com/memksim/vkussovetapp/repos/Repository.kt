package com.memksim.vkussovetapp.repos

import android.util.Log
import com.memksim.vkussovetapp.TAG
import com.memksim.vkussovetapp.datastore.remote.MenuClient
import com.memksim.vkussovetapp.datastore.remote.api.MenuApi
import com.memksim.vkussovetapp.model.MenuRequest
import com.memksim.vkussovetapp.model.SubMenuRequest
import com.memksim.vkussovetapp.view.splashScreen.AppCallback
import com.memksim.vkussovetapp.viewmodel.menuListPage.ListPageCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

    private val api = MenuClient.getClient().create(MenuApi::class.java)


    fun loadSubmenu(callback: ListPageCallback, menuID: String){
        api.getSubMenu(menuID)
            .enqueue(object: Callback<SubMenuRequest>{
                override fun onResponse(
                    call: Call<SubMenuRequest>,
                    response: Response<SubMenuRequest>
                ) {
                    Log.d(TAG, "loadSubMenu() onResponse(): ${response.isSuccessful}")
                    if(response.body() == null){
                        callback.onError()
                    }else{
                        //Log.d(TAG, "onResponse: ${response.body()!!.menuList}")
                        callback.onSuccess(
                            menu = emptyList(),
                            subMenu = response.body()!!.menuList,
                        )
                    }
                }

                override fun onFailure(call: Call<SubMenuRequest>, t: Throwable) {
                    Log.e(TAG, "loadSubMenu() onResponse failure: $t", )
                    callback.onError()
                }

            })
    }

    //load menuList when app is started
    fun loadData(appCallback: AppCallback){
        api.getMenu()
            .enqueue(object: Callback<MenuRequest>{
                override fun onResponse(
                    call: Call<MenuRequest>,
                    response: Response<MenuRequest>
                ) {
                    Log.d(TAG, "loadData() onResponse(): ${response.isSuccessful}")
                    if(response.body() == null){
                        appCallback.onError()
                    }else{
                        appCallback.onLoaded(response.body()!!.menuList)
                    }
                }

                override fun onFailure(call: Call<MenuRequest>, t: Throwable) {
                    Log.e(TAG, "loadData() onResponse failure: $t", )
                    appCallback.onError(/*TODO*/)
                }
            })
    }

    fun getMenu(callback: ListPageCallback){
        api.getMenu()
            .enqueue(object: Callback<MenuRequest>{
                override fun onResponse(
                    call: Call<MenuRequest>,
                    response: Response<MenuRequest>
                ) {
                    Log.d(TAG, "loadMenu() onResponse(): ${response.isSuccessful}")
                    if(response.body() == null){
                        callback.onError()
                    }else{
                        callback.onSuccess(
                            menu = response.body()!!.menuList,
                            subMenu = emptyList()
                        )
                    }
                }

                override fun onFailure(call: Call<MenuRequest>, t: Throwable) {
                    Log.e(TAG, "loadMenu() onResponse failure: $t", )
                    callback.onError()
                }

            })
    }

}