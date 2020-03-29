package com.android.imagessubredditviewer.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.imageloader.Callbacks.ImageLoaderWrapper
import com.android.imageloader.action.ImageLoaderManager
import com.android.imagessubredditviewer.R
import com.android.imagessubredditviewer.adapters.ImageAdapters
import com.android.imagessubredditviewer.listeners.Listener
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject


class MainActivity : AppCompatActivity(), ImageLoaderWrapper, Listener {
    private val arrayListImages = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar()?.hide(); // hide the title bar
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        main_progressBar.visibility = View.VISIBLE
        ImageLoaderManager(this).init()

    }

    /**
     * Recycler view data
     * Adapter for RecyclerView
     */
    private fun setImagesIntoRecyclerView() {
        runOnUiThread(Runnable {
            main_progressBar.visibility = View.GONE
            val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
            recyclerView.isNestedScrollingEnabled = true
            recyclerView.layoutManager = LinearLayoutManager(this)
            val adapter = ImageAdapters(arrayListImages, this, this)
            recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
        })
    }

    /**
     * on Success network call
     */
    override fun onSuccess(response: String) {
        val obj = JSONObject(response)
        val jsonObject: JSONObject = obj.getJSONObject("data")
        val childern = jsonObject.getJSONArray("children")
        arrayListImages.clear()
        for (i in 0 until childern.length()) {
            val childernData = childern.getJSONObject(i)
            val dataChildThumbnail = childernData.getJSONObject("data")
            val thumbnail = dataChildThumbnail.getString("thumbnail")
            Log.d("tagged", ">>>thumbnail" + thumbnail)
            arrayListImages.add(thumbnail)
        }
        setImagesIntoRecyclerView()
    }

    /**
     * on Failure of network Call
     */
    override fun onFailure(throwable: Throwable) {
        Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
    }

    /**
     * onItem click
     * Image Full Screen callback
     */
    override fun onItemClick(url: String?) {
        val intent = Intent(this, FullScreenActivity::class.java)
        intent.putExtra("image", url)
        startActivity(intent)
    }
}
