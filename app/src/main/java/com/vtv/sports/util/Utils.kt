package com.vtv.sports.util

import android.content.Context
import android.support.v7.widget.LinearLayoutManager

/**
 * Created by Giang Long on 12/22/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
class Utils {
    companion object {
        fun getLayoutManagerVer(c: Context): LinearLayoutManager {
            val layoutManager: LinearLayoutManager = LinearLayoutManager(c)
            layoutManager.orientation = LinearLayoutManager.VERTICAL
            return layoutManager
        }

        fun getLayoutManagerHoz(c: Context): LinearLayoutManager {
            val layoutManager: LinearLayoutManager = LinearLayoutManager(c)
            layoutManager.orientation = LinearLayoutManager.HORIZONTAL
            return layoutManager
        }
    }
}