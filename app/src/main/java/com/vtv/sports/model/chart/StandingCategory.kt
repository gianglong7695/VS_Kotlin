package com.vtv.sports.model.chart

import com.google.gson.annotations.SerializedName

/**
 * Created by Giang Long on 1/7/2019.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
data class StandingCategory(
    @SerializedName("CategoryId")
    val categoryId: Int,
    @SerializedName("Name")
    val name: String
)