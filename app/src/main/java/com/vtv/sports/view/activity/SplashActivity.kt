package com.vtv.sports.view.activity

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import com.vtv.sports.R

class SplashActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        startActivity(MainActivity.newIntent(this))
        finish()
    }
}
