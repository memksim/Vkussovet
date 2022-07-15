package com.memksim.vkussovetapp.model

data class SubMenuRequest(
    val status: Boolean,
    val menuList: List<SubMenu>
)

data class SubMenu (
    val id: String,
    val image: String,
    val name: String,
    val content: String,
    val price: String,
    val weight: String,
    val spicy: String
)