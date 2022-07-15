package com.memksim.vkussovetapp.viewmodel.menuListPage

import com.memksim.vkussovetapp.model.Menu
import com.memksim.vkussovetapp.model.ParsedMenu
import com.memksim.vkussovetapp.model.SubMenu
import com.squareup.picasso.RequestCreator

data class MenuListPageState(

    val menuList: List<ParsedMenu>,
    //val subMenuList: List<SubMenu>
    val menuIsNotLoaded: Boolean

)