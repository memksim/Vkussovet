package com.memksim.vkussovetapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.createGraph
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.memksim.vkussovetapp.model.MENU_LIST_TAG
import com.memksim.vkussovetapp.model.Menu

const val TAG = "Test"
class MainActivity : AppCompatActivity() {
    
    private var menuList = listOf<Menu>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        if(savedInstanceState == null){
            val bundle = intent.extras
            menuList = bundle?.getParcelableArrayList<Menu>(MENU_LIST_TAG)?.toList() ?: listOf()
        }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.nav_graph)

        val bundle = Bundle()
        bundle.putParcelableArray(MENU_LIST_TAG, menuList.toTypedArray())

        navHostFragment.navController.setGraph(R.navigation.nav_graph, bundle)

        //initial bottomNav
        val bottomNavView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavView.setupWithNavController(navHostFragment.navController)
        
    }
}