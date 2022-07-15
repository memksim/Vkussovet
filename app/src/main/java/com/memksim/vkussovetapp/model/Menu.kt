package com.memksim.vkussovetapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class MenuRequest(
    val status: Boolean,
    val menuList: List<Menu>
)

const val MENU_LIST_TAG = "MENU_LIST"
@Parcelize
data class Menu(
    val menuID: String,
    val image: String,
    val name: String,
    val subMenuCount: String
): Parcelable


