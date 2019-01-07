package com.vtv.sports.model.chart

import com.google.gson.annotations.SerializedName
import com.vtv.sports.model.common.LastUpdated

/**
 * Created by Giang Long on 1/7/2019.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
class ChartRespone(
    @SerializedName("LastUpdated")
    val lastUpdated: LastUpdated,
    @SerializedName("Standing")
    val standing: List<Standing>,
    @SerializedName("StandingCategory")
    val standingCategory: List<StandingCategory>
)