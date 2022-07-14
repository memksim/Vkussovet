package com.memksim.vkussovetapp.view.menuListPage

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.memksim.vkussovetapp.R
import com.memksim.vkussovetapp.databinding.FragmentMenuListBinding
import com.memksim.vkussovetapp.model.Menu
import com.memksim.vkussovetapp.viewmodel.menuListPage.MenuListPageViewModel

interface ItemClickListener{
    fun onCLick(menuItem: Menu)
}

class FragmentMenuListPage: Fragment(R.layout.fragment_menu_list), ItemClickListener {

    private var _binding: FragmentMenuListBinding? = null
    private val binding get() = _binding

    private lateinit var navController: NavController
    private val args: FragmentMenuListPageArgs by navArgs()

    private lateinit var viewModel: MenuListPageViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[MenuListPageViewModel::class.java]
        viewModel.setData(parseArray(args.menuList))

        val navHostFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navController = navHostFragment.navController

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMenuListBinding.bind(view)

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val dividerItemDecoration = DividerItemDecoration(context, RecyclerView.HORIZONTAL)
        dividerItemDecoration.setDrawable(ResourcesCompat.getDrawable(resources, R.drawable.horizontal_divider, null)!!)
        binding!!.menuList.adapter = MenuAdapter(viewModel.liveData.value!!.menuList, viewModel.liveData.value!!.menuImages, this)
        binding!!.menuList.addItemDecoration(dividerItemDecoration)
    }

    private fun <T> parseArray(arr: Array<T>): List<T> = arr.toList()


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCLick(menuItem: Menu) {
        //TODO
    }

}