package com.memksim.vkussovetapp.view.menuListPage

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.memksim.vkussovetapp.databinding.MenuItemBinding
import com.memksim.vkussovetapp.model.Menu
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator

class MenuAdapter(
    private val menuList: List<Menu>,
    private val menuImagesList: List<RequestCreator>
): RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    inner class MenuViewHolder(private val binding: MenuItemBinding): RecyclerView.ViewHolder(binding.root){
        fun onBind(position: Int){
            menuImagesList[position].into(binding.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MenuItemBinding.inflate(inflater)

        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) = holder.onBind(position)

    override fun getItemCount(): Int = menuList.size
}