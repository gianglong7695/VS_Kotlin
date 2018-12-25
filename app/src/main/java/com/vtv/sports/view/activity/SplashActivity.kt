package com.vtv.sports.view.activity

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import com.vtv.sports.R
import com.vtv.sports.util.Constant

class SplashActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startActivity(MainActivity.newIntent(applicationContext))
            finish()
        }, Constant.DELAY_TO_MAIN)

    }
}
