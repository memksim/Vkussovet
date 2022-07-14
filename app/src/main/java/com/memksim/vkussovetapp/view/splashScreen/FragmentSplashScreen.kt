package com.memksim.vkussovetapp.view.splashScreen

import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.memksim.vkussovetapp.R
import com.memksim.vkussovetapp.databinding.SplashScreenBinding
import com.memksim.vkussovetapp.model.Menu
import com.memksim.vkussovetapp.model.repos.Repository
import com.memksim.vkussovetapp.viewmodel.menuListPage.MenuListPageViewModel

interface AppCallback{
    fun onLoaded(data: List<Menu>)
    fun onError()
}

class FragmentStartScreen: Fragment(R.layout.splash_screen), AppCallback {

    private lateinit var repository: Repository

    private lateinit var navController: NavController

    private var _binding: SplashScreenBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        repository = Repository()

        val navHostFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navController = navHostFragment.navController

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = SplashScreenBinding.bind(view)

        binding!!.progressBar.visibility = View.VISIBLE

        repository.loadData(this)
    }

    override fun onLoaded(data: List<Menu>) {
        navigateNext(data)
    }

    override fun onError() {
        binding!!.progressBar.visibility = View.GONE
        binding!!.errorText.visibility = View.VISIBLE
    }

    private fun navigateNext(data: List<Menu>){
        val action = FragmentStartScreenDirections
            .actionFragmentStartScreenToFragmentMenuListPage(data.toTypedArray())

        Thread.sleep(3000)
        binding!!.progressBar.visibility = View.GONE

        navController.navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}