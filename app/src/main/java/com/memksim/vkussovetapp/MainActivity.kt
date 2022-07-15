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
import com.memksim.vkussovetapp.model.EXTRA_TAG
import com.memksim.vkussovetapp.model.ParsedMenu

const val TAG = "Test"
class MainActivity : AppCompatActivity() {
    
    private var parsedMenuList = listOf<ParsedMenu>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        if(savedInstanceState == null){
            val bundle = intent.extras
            parsedMenuList = bundle?.getParcelableArrayList<ParsedMenu>(EXTRA_TAG)?.toList() ?: listOf()
        }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.nav_graph)

        val bundle = Bundle()
        bundle.putParcelableArray(EXTRA_TAG, parsedMenuList.toTypedArray())

        //graph.addInDefaultArgs(bundle)

        //navHostFragment.navController.graph = graph

        navHostFragment.navController.setGraph(R.navigation.nav_graph, bundle)

        //NavHostFragment.create(R.navigation.nav_graph, bundle)
        
    }
}