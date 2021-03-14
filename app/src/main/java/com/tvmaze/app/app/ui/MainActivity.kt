package com.tvmaze.app.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tvmaze.app.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}