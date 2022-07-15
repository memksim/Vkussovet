package com.memksim.vkussovetapp.view.menuListPage

import android.content.Context
import android.content.res.ColorStateList
import android.net.Uri
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.memksim.vkussovetapp.R
import com.memksim.vkussovetapp.databinding.MenuItemBinding
import com.memksim.vkussovetapp.model.Menu
import com.memksim.vkussovetapp.model.ParsedMenu
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator

class MenuAdapter(
    private val menuList: List<ParsedMenu>,
    private val clickListener: ItemClickListener
): RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {
    private var rowIndex = 0

    inner class MenuViewHolder(val binding: MenuItemBinding, private val context: Context): RecyclerView.ViewHolder(binding.root){

        fun onBind(position: Int){
            Picasso.get().load(menuList[position].image).into(binding.image)
            binding.title.text = menuList[position].name
            binding.counterText.text = "${menuList[position].subMenuCount} товаров"
        }
        fun setSelectedBackground(){
            binding.menuItem.background = ResourcesCompat.getDrawable(context.resources, R.drawable.menu_item_background_selected, null)
        }
        fun setUnselectedBackground(){
            binding.menuItem.background = ResourcesCompat.getDrawable(context.resources, R.drawable.menu_item_background, null)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MenuItemBinding.inflate(inflater)

        return MenuViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.onBind(position)
        holder.binding.menuItem.setOnClickListener {
            rowIndex = holder.adapterPosition
            notifyDataSetChanged()
        }
        if(rowIndex == position){
            holder.setSelectedBackground()
        }else{
            holder.setUnselectedBackground()
        }
    }

    override fun getItemCount(): Int = menuList.size
}