package com.android.imagessubredditviewer.activities

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.android.imagessubredditviewer.R
import com.squareup.picasso.Picasso

/**
 * Full Screen ImageView activity
 */
class FullScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fullscreen_layout)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (intent.extras != null) {
            val url = intent.getStringExtra("image")
            Picasso.with(this).load(url).into((findViewById<ImageView>(R.id.full_screen_url)))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home-> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}