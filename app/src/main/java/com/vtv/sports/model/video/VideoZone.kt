package com.vtv.sports.model.video

import com.google.gson.annotations.SerializedName

/**
 * Created by Giang Long on 12/25/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
data class VideoZone(
        @SerializedName("Id")
        val id: Int,
        @SerializedName("Name")
        val name: String
)