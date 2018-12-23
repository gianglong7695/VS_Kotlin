package com.vtv.sports.util

import android.content.Context
import android.widget.Toast

/**
 * Created by Giang Long on 12/22/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
class ToastUtil {
    companion object {
        fun show(c: Context, msg: String){
            Toast.makeText(c, msg, Toast.LENGTH_SHORT).show()
        }
    }
}