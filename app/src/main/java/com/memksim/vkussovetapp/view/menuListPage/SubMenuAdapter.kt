package com.memksim.vkussovetapp.view.menuListPage

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.memksim.vkussovetapp.R
import com.memksim.vkussovetapp.databinding.SubmenuItemBinding
import com.memksim.vkussovetapp.model.SubMenu
import com.squareup.picasso.Picasso

class SubMenuAdapter(
): RecyclerView.Adapter<SubMenuAdapter.SubMenuViewHolder>() {
    var submenuList: List<SubMenu> = emptyList()

    inner class SubMenuViewHolder(val binding: SubmenuItemBinding,private val context: Context): RecyclerView.ViewHolder(binding.root){
        fun onBind(position: Int){
            Picasso.get().load("https://vkus-sovet.ru/${submenuList[position].image}").into(binding.image)
            binding.nameTitle.text = submenuList[position].name
            binding.typeTitle.text = submenuList[position].content
            binding.priceTitle.text = "${submenuList[position].price}₽"
            binding.weightTitle.text = "/${submenuList[position].weight}"
            if (submenuList[position].spicy == "Y"){
                binding.spicyImg.visibility = View.VISIBLE
            }else{
                binding.spicyImg.visibility = View.GONE
            }
        }

        fun buttonClicked(){
            if(binding.addToCardButton.text.equals(context.resources.getString(R.string.toCart))){
                binding.addToCardButton.apply {
                    setBackgroundColor(ResourcesCompat.getColor(context.resources, R.color.secondary, null))
                    text = context.resources.getString(R.string.inCart)
                }
            }else{
                binding.addToCardButton.apply {
                    setBackgroundColor(ResourcesCompat.getColor(context.resources, R.color.accent, null))
                    text = context.resources.getString(R.string.toCart)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubMenuViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SubmenuItemBinding.inflate(inflater)

        return SubMenuViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: SubMenuViewHolder, position: Int) {
        holder.onBind(position)

        holder.binding.addToCardButton.setOnClickListener {
            holder.buttonClicked()
        }

    }

    override fun getItemCount(): Int = submenuList.size
}