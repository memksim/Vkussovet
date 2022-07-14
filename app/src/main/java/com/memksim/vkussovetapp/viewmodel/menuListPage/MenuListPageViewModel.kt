package com.memksim.vkussovetapp.viewmodel.menuListPage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.memksim.vkussovetapp.model.Menu
import com.memksim.vkussovetapp.model.SubMenu
import com.memksim.vkussovetapp.model.repos.Repository
import com.memksim.vkussovetapp.viewmodel.Callback
import com.squareup.picasso.RequestCreator

class MenuListPageViewModel: ViewModel(), Callback {

    private val repository = Repository()

    private val _data: MutableLiveData<MenuListPageState> by lazy {
        MutableLiveData<MenuListPageState>()
    }
    var liveData: LiveData<MenuListPageState> = _data

    fun getMenuList(){
        repository.getMenu(this)
    }

    fun setData(
        menuList: List<Menu>
    ){
        _data.value = MenuListPageState(
            menuList = menuList,
            menuImages = repository.loadImages(getUrls(menuList)),
            menuIsNotLoaded = false
        )
    }

    override fun onSuccess(menu: List<Menu>, subMenu: List<SubMenu>) {
        _data.value = MenuListPageState(
            menuList = menu,
            menuImages = repository.loadImages(getUrls(menu)),
            menuIsNotLoaded = false
        )
    }

    private fun getUrls(menu: List<Menu>): List<String>{
        val urls = arrayListOf<String>()
        for(i in menu){
            urls.add(i.image)
        }
        return urls
    }

    override fun onError() {
        _data.value = MenuListPageState(
            listOf(),
            listOf(),
            true
        )
    }
}