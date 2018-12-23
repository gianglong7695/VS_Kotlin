package com.vtv.sports.repository

/**
 * Created by Giang Long on 12/22/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
class ApiConstant {
    companion object {

        /* Host server info */
        val middle_path = "vtv-sport-api/app/"
        val host_dev = "http://mic4.channelvn.net/$middle_path"
        val host_release = "http://p2.cnnd.vn/$middle_path"
        var SERVER_DOMIAN = host_release


        /* Key */
        val secret_key_release = "KaPdSgVkYp3s6v9y"
        val secret_key_dev = "VkYp3s5v8y#B!E(H"
        var SECRET_KEY = secret_key_release

        /* Params */
        var FIELD_SECRET_KEY: String = "secret_key"
    }

}