package com.memksim.vkussovetapp.view.splashScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.memksim.vkussovetapp.MainActivity
import com.memksim.vkussovetapp.databinding.ActivityStartScreenBinding
import com.memksim.vkussovetapp.model.MENU_LIST_TAG
import com.memksim.vkussovetapp.model.Menu
import com.memksim.vkussovetapp.repos.Repository

interface AppCallback{
    fun onLoaded(data: List<Menu>)
    fun onError()
}

class StartScreenActivity : AppCompatActivity(), AppCallback {

    private lateinit var repository: Repository

    private lateinit var binding: ActivityStartScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.progressBar.visibility = View.VISIBLE
        repository = Repository()
        //Thread.sleep(4000)
        repository.loadData(this)

    }

    override fun onLoaded(data: List<Menu>) {
        navigateNext(data)
    }

    private fun navigateNext(data: List<Menu>) {

        val intent = Intent(this, MainActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelableArrayList(MENU_LIST_TAG, data as ArrayList)
        intent.putExtras(bundle)

        Thread.sleep(2000)
        binding.progressBar.visibility = View.GONE

        startActivity(intent)
        finish()
    }



    override fun onError() {
        binding.progressBar.visibility = View.GONE
        binding.errorText.visibility = View.VISIBLE
    }
}