package com.memksim.vkussovetapp.view.splashScreen

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.memksim.vkussovetapp.MainActivity
import com.memksim.vkussovetapp.R
import com.memksim.vkussovetapp.databinding.ActivityStartScreenBinding
import com.memksim.vkussovetapp.model.EXTRA_TAG
import com.memksim.vkussovetapp.model.Menu
import com.memksim.vkussovetapp.model.ParsedMenu
import com.memksim.vkussovetapp.model.repos.Repository
import com.squareup.picasso.Picasso

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
        Thread.sleep(4000)
        repository.loadData(this)

    }

    override fun onLoaded(data: List<Menu>) {
        navigateNext(data)
    }

    private fun navigateNext(data: List<Menu>) {

        val list = parsedList(data)
        val intent = Intent(this, MainActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelableArrayList(EXTRA_TAG, list as ArrayList)
        intent.putExtras(bundle)
        Thread.sleep(1000)
        binding.progressBar.visibility = View.GONE
        startActivity(intent)
    }

    private fun parsedList(data: List<Menu>): List<ParsedMenu> {
        val resultList = arrayListOf<ParsedMenu>()
        for (i in data){
            resultList.add(
                ParsedMenu(
                    menuID = i.menuID,
                    image = "https://vkus-sovet.ru/${i.image}",
                    name = i.name,
                    subMenuCount = i.subMenuCount
                )
            )
        }

        return resultList
    }

    override fun onError() {
        binding.progressBar.visibility = View.GONE
        binding.errorText.visibility = View.VISIBLE
    }
}