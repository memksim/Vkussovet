package com.memksim.vkussovetapp.viewmodel

import com.memksim.vkussovetapp.model.Menu
import com.memksim.vkussovetapp.model.SubMenu

interface Callback {
    fun onSuccess(menu: List<Menu> = listOf(), subMenu: List<SubMenu> = listOf())
    fun onError()
}