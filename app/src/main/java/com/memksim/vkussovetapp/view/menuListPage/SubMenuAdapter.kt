package com.memksim.vkussovetapp.view.menuListPage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.memksim.vkussovetapp.databinding.SubmenuItemBinding
import com.memksim.vkussovetapp.model.SubMenu
import com.squareup.picasso.Picasso

class SubMenuAdapter(
): RecyclerView.Adapter<SubMenuAdapter.SubMenuViewHolder>() {
    var submenuList: List<SubMenu> = emptyList()

    inner class SubMenuViewHolder(val binding: SubmenuItemBinding): RecyclerView.ViewHolder(binding.root){
        fun onBind(position: Int){
            Picasso.get().load("https://vkus-sovet.ru/${submenuList[position].image}").into(binding.image)
            binding.nameTitle.text = submenuList[position].name
            binding.typeTitle.text = submenuList[position].content
            binding.priceTitle.text = "${submenuList[position].price}₽"
            binding.weightTitle.text = "/${submenuList[position].weight}г."
            if (submenuList[position].spicy == "Y"){
                binding.spicyImg.visibility = View.VISIBLE
            }else{
                binding.spicyImg.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubMenuViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SubmenuItemBinding.inflate(inflater)

        return SubMenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubMenuViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int = submenuList.size
}