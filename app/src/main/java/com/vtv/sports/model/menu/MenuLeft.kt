package com.vtv.sports.model.menu

import com.google.gson.annotations.SerializedName

/**
 * Created by Giang Long on 12/22/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
data class MenuLeft(
    @SerializedName("ZoneId") val zoneId: Int,
    @SerializedName("ZoneName") val zoneName: String,
    @SerializedName("ZoneShortURL") val zoneShortURL: String,
    var imageRes: Int
)