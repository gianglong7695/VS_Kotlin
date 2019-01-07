package com.vtv.sports.model.chart

import com.google.gson.annotations.SerializedName

/**
 * Created by Giang Long on 1/7/2019.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
data class Standing(
    @SerializedName("Diff")
    val diff: Int,
    @SerializedName("Match")
    val match: Int,
    @SerializedName("Rank")
    val rank: Int,
    @SerializedName("Scrope")
    val scrope: Int,
    @SerializedName("Team")
    val team: String
)