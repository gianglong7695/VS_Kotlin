package com.vtv.sports.view.activity

import android.app.Activity
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.CountDownTimer
import com.vtv.sports.R
import com.vtv.sports.databinding.ActivitySplashBinding
import com.vtv.sports.util.Constant
import com.vtv.sports.util.Logs
import com.vtv.sports.util.Utils

class SplashActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView(this, R.layout.activity_splash) as ActivitySplashBinding

        Utils.after(Constant.DELAY_TO_MAIN) {
            startActivity(MainActivity.newIntent(applicationContext))
            finish()
        }

        binding.progressLoading.progress = 0

        object : CountDownTimer(Constant.DELAY_TO_MAIN, 100) {
            override fun onFinish() {
                Logs.d("Done!")

            }

            override fun onTick(millisUntilFinished: Long) {
                binding.progressLoading.progress = ((Constant.DELAY_TO_MAIN - millisUntilFinished) * 100 / Constant.DELAY_TO_MAIN).toInt()
                Logs.d(millisUntilFinished.toString())
            }

        }.start()

    }
}
