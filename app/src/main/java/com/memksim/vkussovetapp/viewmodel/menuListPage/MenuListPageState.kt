package com.memksim.vkussovetapp.viewmodel.menuListPage

import com.memksim.vkussovetapp.model.Menu
import com.memksim.vkussovetapp.model.SubMenu

data class MenuListPageState(

    val menuList: List<Menu>,
    //val subMenuList: List<SubMenu>
    val menuIsNotLoaded: Boolean

)