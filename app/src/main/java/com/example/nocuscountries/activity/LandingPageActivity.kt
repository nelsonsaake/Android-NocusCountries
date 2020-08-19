package com.example.nocuscountries.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nocuscountries.R
import kotlinx.android.synthetic.main.content_landing_page.*

class LandingPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)

        allButton.setOnClickListener {
            startActivity(Intent(applicationContext, CountryListActivity::class.java))
        }
    }
}