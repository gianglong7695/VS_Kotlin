package com.vtv.sports.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * Created by Giang Long on 12/23/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
class NetworkUtil {
    companion object {
        private val TYPE_NOT_CONNECTED = 1
        private val TYPE_WIFI = 2
        private val TYPE_3G = 3

//        fun hasConnected(c: Context): Boolean {
//            return getConnectivityStatus(c) != TYPE_NOT_CONNECTED
//        }
//
//        private fun getConnectivityStatus(c: Context): Int {
//            val connect = c.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//            val networkInfo = connect.getActiveNetworkInfo()
//            if (networkInfo != null) {
//                if (networkInfo.type == ConnectivityManager.TYPE_WIFI) {
//                    return TYPE_WIFI
//                } else if (networkInfo.type == ConnectivityManager.TYPE_MOBILE) {
//                    return TYPE_3G
//                }
//            }
//            return TYPE_NOT_CONNECTED
//        }

        fun isNetworkAvailable(c: Context): Boolean {
            val connectivityManager = c.getSystemService(Context.CONNECTIVITY_SERVICE)
            return if (connectivityManager is ConnectivityManager) {
                val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
                networkInfo?.isConnected ?: false
            } else false
        }
    }
}