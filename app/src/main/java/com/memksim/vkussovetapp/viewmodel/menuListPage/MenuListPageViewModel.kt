package com.memksim.vkussovetapp.viewmodel.menuListPage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.memksim.vkussovetapp.model.Menu
import com.memksim.vkussovetapp.model.SubMenu
import com.memksim.vkussovetapp.model.repos.Repository
import com.memksim.vkussovetapp.viewmodel.Callback

class MenuListPageViewModel: ViewModel(), Callback {

    private val repository = Repository()

    private val _data: MutableLiveData<MenuListPageState> by lazy {
        MutableLiveData<MenuListPageState>()
    }
    var liveData: LiveData<MenuListPageState> = _data

    fun getMenuList(){
        repository.getMenu(this)
    }

    override fun onSuccess(menu: List<Menu>, subMenu: List<SubMenu>) {
        _data.value = MenuListPageState(
            menuList = menu,
            menuIsNotLoaded = false
        )
    }

    override fun onError() {
        _data.value = MenuListPageState(
            listOf(),
            true
        )
    }
}