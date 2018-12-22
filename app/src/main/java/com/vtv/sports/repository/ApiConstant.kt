package com.vtv.sports.repository

/**
 * Created by Giang Long on 12/22/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
class ApiConstant {
    companion object {
        /* Config */
        val isDeveloperMode = false


        /* Host server info */
        val host_dev = "http://mic4.channelvn.net/"
        val host_release = "http://p2.cnnd.vn/"
        val middle_path = "vtv-sport-api/app/"

        var SERVER_DOMIAN = fun(): String {
            if (isDeveloperMode)
                return "$host_dev$middle_path"
            else
                return "$host_release$middle_path"
        }


        /* Key */
        val secret_key_release = "KaPdSgVkYp3s6v9y"
        val secret_key_dev = "VkYp3s5v8y#B!E(H"

        var SECRET_KEY = fun(): String {
            if (isDeveloperMode)
                return secret_key_dev
            else
                return secret_key_release
        }
    }

}