package com.vtv.sports.view.activity

import android.app.Activity
import android.os.Bundle
import com.vtv.sports.R
import com.vtv.sports.util.Constant
import com.vtv.sports.util.Utils

class SplashActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Utils.after(Constant.DELAY_TO_MAIN) {
            startActivity(MainActivity.newIntent(applicationContext))
            finish()
        }
    }
}
