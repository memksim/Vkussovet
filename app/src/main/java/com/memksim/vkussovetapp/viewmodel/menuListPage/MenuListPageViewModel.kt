package com.memksim.vkussovetapp.viewmodel.menuListPage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.memksim.vkussovetapp.TAG
import com.memksim.vkussovetapp.model.Menu
import com.memksim.vkussovetapp.model.SubMenu
import com.memksim.vkussovetapp.repos.Repository

interface ListPageCallback {
    fun onSuccess(menu: List<Menu> = listOf(), subMenu: List<SubMenu> = listOf())
    fun onError()
}

class MenuListPageViewModel: ViewModel(), ListPageCallback {

    private val repository = Repository()

    private val _data: MutableLiveData<MenuListPageState> by lazy {
        MutableLiveData<MenuListPageState>()
    }
    var liveData: LiveData<MenuListPageState> = _data

    fun getMenuList(){
        repository.getMenu(this)
    }

    fun getSubmenu(menuID: String){
        repository.loadSubmenu(this, menuID)
    }

    //called when fragment is created
    fun setData(
        menuList: List<Menu>
    ){
        _data.value = MenuListPageState(
            menuList[0],
            menuList = menuList,
            emptyList(),
            menuIsNotLoaded = false
        )
    }

    fun setSelectedItem(menuItem: Menu){
        _data.value = MenuListPageState(
            selectedItem = menuItem,
            menuList = _data.value!!.menuList,
            subMenuList = _data.value!!.subMenuList,
            menuIsNotLoaded = false
        )
    }

    override fun onSuccess(menu: List<Menu>, subMenu: List<SubMenu>) {

        val state: MenuListPageState =
            if(menu.isEmpty()){
                MenuListPageState(
                    selectedItem = _data.value!!.selectedItem,
                    menuList = _data.value!!.menuList,
                    subMenuList = subMenu,
                    menuIsNotLoaded = false
                )
            } else{
                MenuListPageState(
                    selectedItem = menu[0],
                    menuList = menu,
                    subMenuList = _data.value!!.subMenuList,
                    menuIsNotLoaded = false
                )
            }

        Log.d(TAG, "onSuccess: ${state.toString()}")

        _data.value = state
    }

    override fun onError() {
        _data.value = MenuListPageState(
            Menu(
                "",
                "",
                "",
                ""
            ),
            emptyList(),
            emptyList(),
            true
        )
    }

}