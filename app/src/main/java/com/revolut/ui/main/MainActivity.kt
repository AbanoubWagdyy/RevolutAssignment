package com.revolut.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.revolut.ui.converter.ui.ConverterFragment
import com.revolut.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.frame, ConverterFragment.newInstance()).commitAllowingStateLoss()
    }
}