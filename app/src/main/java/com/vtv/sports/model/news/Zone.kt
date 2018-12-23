package com.vtv.sports.model.news

import com.google.gson.annotations.SerializedName

/**
 * Created by Giang Long on 12/23/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
data class Zone(
    @SerializedName("Id") val id: Int,
    @SerializedName("Name") val name: String,
    @SerializedName("ZoneShortURL") val zoneShortURL: String,
    @SerializedName("ParentId") val parentId: Int
)