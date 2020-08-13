package com.example.nocuscountries.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nocuscountries.R
import com.google.android.material.appbar.CollapsingToolbarLayout
import kotlinx.android.synthetic.main.content_scrolling.*

class LandingPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_landing_page)

        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = title

        allButton.setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }
    }
}