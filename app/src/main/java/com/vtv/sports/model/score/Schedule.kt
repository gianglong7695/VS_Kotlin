package com.vtv.sports.model.score

import com.google.gson.annotations.SerializedName

/**
 * Created by Giang Long on 1/7/2019.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
data class Schedule(
    @SerializedName("AwayLogo")
    val awayLogo: String,
    @SerializedName("AwayName")
    val awayName: String,
    @SerializedName("AwayScore")
    val awayScore: String,
    @SerializedName("Date")
    val date: String,
    @SerializedName("HomeLogo")
    val homeLogo: String,
    @SerializedName("HomeName")
    val homeName: String,
    @SerializedName("HomeScore")
    val homeScore: String,
    @SerializedName("NewsUrl")
    val newsUrl: String,
    @SerializedName("Status")
    val status: Int
)